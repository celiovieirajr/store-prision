package com.example.prision.modules.service;

import com.example.prision.modules.dto.SaleRequestDto;
import com.example.prision.modules.dto.SaleResponseDto;
import com.example.prision.modules.mapper.SaleMapper;
import com.example.prision.modules.model.ItemSale;
import com.example.prision.modules.model.Product;
import com.example.prision.modules.model.Sale;
import com.example.prision.modules.repository.ItemSaleRepository;
import com.example.prision.modules.repository.ProductRepository;
import com.example.prision.modules.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class SaleServiceImplements implements ISaleService{

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;

    public SaleServiceImplements(SaleRepository saleRepository, SaleMapper saleMapper, ProductRepository productRepository, ItemSaleRepository itemSaleRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.productRepository = productRepository;
        this.itemSaleRepository = itemSaleRepository;
    }

    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        if (saleRequestDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request dto is Empty");
        }

        Sale sale = saleMapper.toModel(saleRequestDto);
        Sale saleSaved = saleRepository.save(sale);

        return saleMapper.toResponseDto(saleSaved);
    }


    public SaleResponseDto findById(Long idSale) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request dto is Empty");
        }

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item sale Request dto Not Found"));

        return saleMapper.toResponseDto(sale);
    }


    public List<SaleResponseDto> findAll() {
        return saleRepository.findAll().stream().map(saleMapper::toResponseDto).toList();
    }


    public SaleResponseDto updatedSale(Long idSale, SaleRequestDto requestDto) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request dto is Empty");
        }

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item sale Request dto Not Found"));


        if (sale.getItemSalesList().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request dto is Empty");
        }

        sale.getItemSalesList().clear();

        for (var itemDto : requestDto.getItemSaleRequestDtosList()) {
            ItemSale itemSale = new ItemSale();
            List<ItemSale> listItens = sale.getItemSalesList();

            Product product = productRepository.findById(itemDto.getIdProductRequestDto()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Products no exists"));
            itemSale.setProduct(product);

            int quantity = itemDto.getQuantity();
            BigDecimal amount = product.getAmount();

            itemSale.setQuantity(quantity);
            itemSale.setAmount(amount);

            BigDecimal totalAmount = amount.multiply(BigDecimal.valueOf(quantity));
            itemSale.setTotalAmount(totalAmount);

            itemSale.setSale(sale);
            listItens.add(itemSale);
        }

        Sale saved = recalculateTotalAmount(sale);
        return saleMapper.toResponseDto(saved);
    }

    public void deleteById(Long idSale) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request dto is Empty");
        }

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item sale Request dto Not Found"));

        saleRepository.delete(sale);
    }


    public Sale recalculateTotalAmount (Sale sale) {
        BigDecimal total = sale.getItemSalesList() == null
                ? BigDecimal.ZERO
                : sale.getItemSalesList().stream()
                .map(ItemSale::getTotalAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        sale.setTotalAmount(total);
        saleRepository.save(sale);

        return sale;
    }
}
