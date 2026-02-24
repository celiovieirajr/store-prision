package com.example.prision.modules.mapper;

import com.example.prision.modules.dto.SaleRequestDto;
import com.example.prision.modules.dto.SaleResponseDto;
import com.example.prision.modules.model.ItemSale;
import com.example.prision.modules.model.Penitentiary;
import com.example.prision.modules.model.Sale;
import com.example.prision.modules.repository.PenitentiaryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SaleMapper {

    private final ItemSaleMapper itemSaleMapper;
    private final PenitentiaryRepository penitentiaryRepository;

    public SaleMapper(ItemSaleMapper itemSaleMapper, PenitentiaryRepository penitentiaryRepository) {
        this.itemSaleMapper = itemSaleMapper;
        this.penitentiaryRepository = penitentiaryRepository;
    }

    public Sale toModel(SaleRequestDto saleRequestDto) {
        Sale sale = new Sale();

        Penitentiary penitentiary = penitentiaryRepository.findById(saleRequestDto.getIdPenitentiaryRequestDto()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penitentiary Not Found"));

        List<ItemSale> itemSaleList = saleRequestDto.getItemSaleRequestDtosList()
                .stream()
                .map(itemSale -> itemSaleMapper.toModel(itemSale))
                .toList();

        sale.setItemSalesList(itemSaleList);
        sale.setTotalAmount(BigDecimal.valueOf(0));

        sale.setPenitentiary(penitentiary);
        sale.setNameCustomerReceiver(saleRequestDto.getNameCustomerReceiver());
        sale.setNameCustomerSender(saleRequestDto.getNameCustomerSender());
        sale.setCustomerSenderPhone(saleRequestDto.getCustomerSenderPhone());

        return sale;
    }

    public SaleResponseDto toResponseDto(Sale sale){
        SaleResponseDto response = new SaleResponseDto();

       response.setItemSaleResponseDtoList(
               sale.getItemSalesList()
                       .stream()
                       .map(itemSaleMapper::toResponseDto)
                       .toList());

        response.setId(sale.getId());
        response.setNameCustomerReceiver(sale.getNameCustomerReceiver());
        response.setNameCustomerSender(sale.getNameCustomerSender());
        response.setCustomerSenderPhone(sale.getCustomerSenderPhone());
        response.setTotalAmount(sale.getTotalAmount());
        return response;
    }
}
