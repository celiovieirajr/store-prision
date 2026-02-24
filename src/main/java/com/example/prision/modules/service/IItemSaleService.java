package com.example.prision.modules.service;

import com.example.prision.modules.dto.ItemSaleRequestDto;
import com.example.prision.modules.dto.ItemSaleResponseDto;

import java.util.List;

public interface IItemSaleService {
    ItemSaleResponseDto insertItemSale(ItemSaleRequestDto itemSaleRequestDto, Long idSale);
    ItemSaleResponseDto findById(Long idSale, Long idItemSale);
    List<ItemSaleResponseDto> findAll(Long idSale);
    ItemSaleResponseDto updateItemById(Long idSale, ItemSaleRequestDto itemSaleRequestDto, Long idItemSale);
    void deleteById(Long idSale, Long idItemSale);
}
