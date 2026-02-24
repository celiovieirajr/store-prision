package com.example.prision.modules.mapper;

import com.example.prision.modules.dto.ItemSaleRequestDto;
import com.example.prision.modules.dto.ItemSaleResponseDto;
import com.example.prision.modules.model.ItemSale;
import com.example.prision.modules.model.Product;
import com.example.prision.modules.model.Sale;
import com.example.prision.modules.repository.ProductRepository;
import com.example.prision.modules.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ItemSaleMapper {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public ItemSaleMapper(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public ItemSale toModel(ItemSaleRequestDto itemSaleRequestDto) {
        ItemSale itemSale = new ItemSale();

        Sale sale = saleRepository.findById(itemSaleRequestDto.getIdSaleRequestDto()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item sale not found: " +  itemSaleRequestDto.getIdSaleRequestDto()));

        Product product = productRepository.findById(itemSaleRequestDto.getIdProductRequestDto()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found: " + itemSaleRequestDto.getIdProductRequestDto()));

        itemSale.setSale(sale);
        itemSale.setProduct(product);
        itemSale.setQuantity(itemSaleRequestDto.getQuantity());
        itemSale.setAmount(product.getAmount());

        BigDecimal quantity = BigDecimal.valueOf(itemSaleRequestDto.getQuantity());
        BigDecimal amountTotal = quantity.multiply(quantity).setScale(2, RoundingMode.HALF_UP);
        itemSaleRequestDto.setTotalAmount(amountTotal);

        return itemSale;
    }

    public ItemSaleResponseDto toResponseDto(ItemSale itemSale) {
        ItemSaleResponseDto itemSaleResponseDto = new ItemSaleResponseDto();
        itemSaleResponseDto.setId(itemSale.getId());

        itemSaleResponseDto.setQuantity(itemSale.getQuantity());
        itemSaleResponseDto.setAmount(itemSale.getAmount());
        itemSaleResponseDto.setTotalAmount(itemSale.getTotalAmount());
        return itemSaleResponseDto;
    }
}
