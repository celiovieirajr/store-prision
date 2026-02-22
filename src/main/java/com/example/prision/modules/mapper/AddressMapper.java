package com.example.prision.modules.mapper;

import com.example.prision.modules.dto.AddressRequestDto;
import com.example.prision.modules.dto.AddressResponse;
import com.example.prision.modules.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toModel(AddressRequestDto addressRequestDto) {
        Address address = new Address();

        address.setCep(addressRequestDto.getCep());
        address.setLogradouro(addressRequestDto.getLogradouro());
        address.setComplemento(addressRequestDto.getComplemento());
        address.setUnidade(addressRequestDto.getUnidade());
        address.setBairro(addressRequestDto.getBairro());
        address.setUf(addressRequestDto.getUf());
        address.setEstado(addressRequestDto.getEstado());
        address.setRegiao(addressRequestDto.getRegiao());
        address.setIbge(addressRequestDto.getIbge());

        return address;
    }

    public AddressResponse toResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setCep(address.getCep());
        addressResponse.setLogradouro(address.getLogradouro());
        addressResponse.setComplemento(address.getComplemento());
        addressResponse.setUnidade(address.getUnidade());
        addressResponse.setBairro(address.getBairro());
        addressResponse.setUf(address.getUf());
        addressResponse.setEstado(address.getEstado());
        addressResponse.setRegiao(address.getRegiao());
        addressResponse.setIbge(address.getIbge());

        return addressResponse;
    }
}
