package com.example.prision.modules.service;

import com.example.prision.modules.dto.ItemSaleRequestDto;
import com.example.prision.modules.dto.ItemSaleResponseDto;
import com.example.prision.modules.mapper.ItemSaleMapper;
import com.example.prision.modules.mapper.ProductMapper;
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

import java.util.List;

@Service
public class ItemSaleImplements implements IItemSaleService {

    private final ItemSaleRepository itemSaleRepository;
    private final ItemSaleMapper itemSaleMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public ItemSaleImplements(ItemSaleRepository itemSaleRepository, ItemSaleMapper itemSaleMapper, ProductRepository productRepository, ProductMapper productMapper,  SaleRepository saleRepository, SaleMapper saleMapper) {
        this.itemSaleRepository = itemSaleRepository;
        this.itemSaleMapper = itemSaleMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    public ItemSaleResponseDto insertItemSale(ItemSaleRequestDto itemSaleRequestDto, Long idSale) {
        if (idSale == null || idSale <= 0) {
            throw new IllegalArgumentException();
        }

        if (itemSaleRequestDto.getIdProductRequestDto() == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request dto is Empty");
        }

        if (itemSaleRequestDto.getQuantity() == null || itemSaleRequestDto.getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request is Empty");
        }

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale is nots exists"));

        Product product = productRepository.findById(itemSaleRequestDto.getIdProductRequestDto()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product nots exists"));

        ItemSale model = itemSaleMapper.toModel(itemSaleRequestDto);
        ItemSale modelSaved = itemSaleRepository.save(model);

        return  itemSaleMapper.toResponseDto(modelSaved);
    }

    public ItemSaleResponseDto findById(Long idSale, Long idItemSale) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale Request is Empty");
        }

        if (idItemSale == null || idItemSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request is Empty");
        }

        ItemSale model = itemSaleRepository.findById(idItemSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item is nots exists"));

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sale is nots exists"));

        if (!model.getSale().getId().equals(sale.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Sale and Sale are not the same");
        }

        return itemSaleMapper.toResponseDto(model);
    }


    public List<ItemSaleResponseDto> findAll(Long idSale) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale Request is Empty");
        }

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale is nots exists"));

        if (sale.getId() == idSale) {
            return itemSaleRepository.findAll().stream().map(itemSaleMapper::toResponseDto).toList();
        }

        return List.of(); // return (List<E>) ImmutableCollections.EMPTY_LIST;
    }


    public ItemSaleResponseDto updateItemById(Long idSale, ItemSaleRequestDto itemSaleRequestDto, Long idItemSale) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale Request is Empty");
        }

        if (idItemSale == null || idItemSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request is Empty");
        }

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale is nots exists"));

        ItemSale itemSale = itemSaleRepository.findById(idItemSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item is nots exists"));

        Product product = productRepository.findById(itemSaleRequestDto.getIdProductRequestDto()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product is nots exists"));

        itemSale.setProduct(product);
        itemSale.setQuantity(itemSaleRequestDto.getQuantity());
        itemSale.setAmount(product.getAmount());

        return itemSaleMapper.toResponseDto(itemSaleRepository.save(itemSale));
    }

    public void deleteById(Long idSale, Long idItemSale) {
        if (idSale == null || idSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale Request is Empty");
        }

        if (idItemSale == null || idItemSale <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sale Request is Empty");
        }

        ItemSale itemSale = itemSaleRepository.findById(idItemSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item is nots exists"));

        Sale sale = saleRepository.findById(idSale).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sale is nots exists"));

        if (itemSale.getSale().getId().equals(sale.getId())) {
            itemSaleRepository.delete(itemSale);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error different idSale or idItemSale");
        }
    }


}
