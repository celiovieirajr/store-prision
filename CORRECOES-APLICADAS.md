# Correção dos Problemas da API de Venda

## Problemas Identificados

### 1. Dependência Circular entre Mappers ❌

**Erro:**
```
The dependencies of some of the beans in the application context form a cycle:
   itemSaleMapper -> saleMapper -> itemSaleMapper
```

**Causa:**
- `SaleMapper` dependia de `ItemSaleMapper` (injetado no construtor)
- `ItemSaleMapper` dependia de `SaleRepository` para buscar a `Sale` no método `toModel()`
- Isso criava uma dependência circular: SaleMapper → ItemSaleMapper → SaleRepository → SaleMapper

**Solução Aplicada:**
- Removi a injeção de `SaleRepository` do `ItemSaleMapper`
- Removi a linha que buscava e setava a `Sale` no método `toModel()` do `ItemSaleMapper`
- Adicionei a lógica no `SaleMapper` para setar a referência bidirecional entre `Sale` e `ItemSale` após criar os itens
- Corrigi o cálculo do `totalAmount` (estava multiplicando quantity * quantity ao invés de amount * quantity)

### 2. Erro 404 no Endpoint POST /api/v1/sale/ ❌

**Causa:**
O JSON enviado tinha campos inconsistentes que não existiam no DTO:

```json
{
  "id": 2,
  "idSaleRequestDto": null,        // ❌ Campo errado
  "idProductRequestDto": 2,        // ❌ Campo errado
  "quantity": 1,
  "totalAmount": 29.99
}
```

Os campos corretos no `ItemSaleRequestDto` são:
- `idSale` (não `idSaleRequestDto`)
- `idProduct` (não `idProductRequestDto`)

**JSON Correto:**
```json
{
  "itemSaleRequestDtosList": [
    {
      "idSale": null,              // ✅ Correto
      "idProduct": 1,              // ✅ Correto
      "quantity": 2,
      "totalAmount": 59.98
    },
    {
      "idSale": null,              // ✅ Correto
      "idProduct": 2,              // ✅ Correto
      "quantity": 1,
      "totalAmount": 29.99
    }
  ],
  "nameCustomerSender": "João da Silva",
  "customerSenderPhone": "+55 11 99999-0000",
  "nameCustomerReceiver": "Maria Souza",
  "idPenitentiaryRequestDto": 1,
  "totalAmount": 89.97
}
```

## Alterações Realizadas

### ItemSaleMapper.java

**Antes:**
```java
private final SaleRepository saleRepository;
private final ProductRepository productRepository;

public ItemSaleMapper(SaleRepository saleRepository, ProductRepository productRepository) {
    this.saleRepository = saleRepository;
    this.productRepository = productRepository;
}

public ItemSale toModel(ItemSaleRequestDto itemSaleRequestDto) {
    ItemSale itemSale = new ItemSale();
    
    Sale sale = saleRepository.findById(itemSaleRequestDto.getIdSale()).orElseThrow(...);
    
    itemSale.setSale(sale);
    // ...
    BigDecimal amountTotal = quantity.multiply(quantity); // ❌ Erro: quantity * quantity
}
```

**Depois:**
```java
private final ProductRepository productRepository;

public ItemSaleMapper(ProductRepository productRepository) {
    this.productRepository = productRepository;
}

public ItemSale toModel(ItemSaleRequestDto itemSaleRequestDto) {
    ItemSale itemSale = new ItemSale();
    
    // Não busca mais a Sale aqui para evitar dependência circular
    
    // itemSale.setSale(sale); // Removido - será feito no SaleMapper
    // ...
    BigDecimal amountTotal = product.getAmount().multiply(quantity); // ✅ amount * quantity
}
```

### SaleMapper.java

**Antes:**
```java
List<ItemSale> itemSaleList = saleRequestDto.getItemSaleRequestDtosList()
        .stream()
        .map(itemSale -> itemSaleMapper.toModel(itemSale))
        .toList();

sale.setItemSalesList(itemSaleList);
sale.setTotalAmount(BigDecimal.valueOf(0)); // ❌ Total sempre zero
```

**Depois:**
```java
List<ItemSale> itemSaleList = saleRequestDto.getItemSaleRequestDtosList()
        .stream()
        .map(itemSale -> itemSaleMapper.toModel(itemSale))
        .toList();

// Define a referência bidirecional entre Sale e ItemSale
itemSaleList.forEach(itemSale -> itemSale.setSale(sale));

sale.setItemSalesList(itemSaleList);

// Calcula o total amount baseado nos itens ✅
BigDecimal totalAmount = itemSaleList.stream()
        .map(ItemSale::getTotalAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
sale.setTotalAmount(totalAmount);
```

## Como Testar

### 1. Reinicie a aplicação
```bash
./mvnw spring-boot:run
```

### 2. Use o JSON correto
Arquivo: `sale-request-example.json` foi criado na raiz do projeto com o exemplo correto.

### 3. Faça o request POST
```bash
POST http://localhost:8080/api/v1/sale/
Content-Type: application/json

{
  "itemSaleRequestDtosList": [
    {
      "idSale": null,
      "idProduct": 1,
      "quantity": 2,
      "totalAmount": 59.98
    },
    {
      "idSale": null,
      "idProduct": 2,
      "quantity": 1,
      "totalAmount": 29.99
    }
  ],
  "nameCustomerSender": "João da Silva",
  "customerSenderPhone": "+55 11 99999-0000",
  "nameCustomerReceiver": "Maria Souza",
  "idPenitentiaryRequestDto": 1,
  "totalAmount": 89.97
}
```

## Verificações Importantes

1. ✅ A aplicação deve iniciar sem o erro de dependência circular
2. ✅ O endpoint POST /api/v1/sale/ deve retornar 201 Created
3. ✅ O totalAmount deve ser calculado corretamente baseado nos itens
4. ✅ Os itens devem estar associados corretamente à venda

## Observações

- O campo `idSale` nos itens pode ser `null` no request de criação, pois a venda ainda não foi criada
- O `totalAmount` será recalculado automaticamente pelo backend baseado nos produtos e quantidades
- Certifique-se de que os produtos com `idProduct: 1` e `idProduct: 2` existem no banco de dados
- Certifique-se de que a penitenciária com `idPenitentiaryRequestDto: 1` existe no banco de dados

