package com.example.prision.modules.mapper;

import com.example.prision.modules.dto.ItemSaleRequestDto;
import com.example.prision.modules.dto.ItemSaleResponseDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.dto.SaleResponseDto;
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
    private final ProductMapper productMapper;

    public ItemSaleMapper(SaleRepository saleRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ItemSale toModel(ItemSaleRequestDto itemSaleRequestDto) {
        ItemSale itemSale = new ItemSale();

        Product product = productRepository.findById(itemSaleRequestDto.getIdProduct()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found: " +
                        itemSaleRequestDto.getIdProduct()));

        itemSale.setProduct(product);
        itemSale.setQuantity(itemSaleRequestDto.getQuantity());
        itemSale.setAmount(product.getAmount());

        BigDecimal quantity = BigDecimal.valueOf(itemSaleRequestDto.getQuantity());
        BigDecimal amountTotal = product.getAmount().multiply(quantity).setScale(2, RoundingMode.HALF_UP);
        itemSale.setTotalAmount(amountTotal);

        return itemSale;
    }

    public ItemSaleResponseDto toResponseDto(ItemSale itemSale) {
        ItemSaleResponseDto itemSaleResponseDto = new ItemSaleResponseDto();
        itemSaleResponseDto.setId(itemSale.getId());

        itemSaleResponseDto.setQuantity(itemSale.getQuantity());
        itemSaleResponseDto.setTotalAmount(itemSale.getTotalAmount());

        ProductResponseDto productResponseDto = productMapper.toResponseDto(itemSale.getProduct());
        itemSaleResponseDto.setProduct(productResponseDto);

        return itemSaleResponseDto;
    }
}
