package com.example.prision.modules.mapper;

import com.example.prision.modules.dto.AddressResponse;
import com.example.prision.modules.dto.PenitentiaryRequestDto;
import com.example.prision.modules.dto.PenitentiaryResponseDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.model.Address;
import com.example.prision.modules.model.Penitentiary;
import org.springframework.stereotype.Component;

@Component
public class PenitentiaryMapper {

    private final AddressMapper addressMapper;

    public  PenitentiaryMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Penitentiary toModel(PenitentiaryRequestDto penitentiaryRequestDto) {
        Penitentiary penitentiary = new Penitentiary();
        penitentiary.setName(penitentiaryRequestDto.getName());

        Address address = new Address();
        address.setCep(penitentiaryRequestDto.getAddressRequestDto().getCep());
        address.setLogradouro(penitentiaryRequestDto.getAddressRequestDto().getLogradouro());
        address.setComplemento(penitentiaryRequestDto.getAddressRequestDto().getComplemento());
        address.setUnidade(penitentiaryRequestDto.getAddressRequestDto().getUnidade());
        address.setBairro(penitentiaryRequestDto.getAddressRequestDto().getBairro());
        address.setUf(penitentiaryRequestDto.getAddressRequestDto().getUf());
        address.setEstado(penitentiaryRequestDto.getAddressRequestDto().getEstado());
        address.setRegiao(penitentiaryRequestDto.getAddressRequestDto().getRegiao());
        address.setIbge(penitentiaryRequestDto.getAddressRequestDto().getIbge());

        penitentiary.setAddress(address);

        return  penitentiary;
    }

    public PenitentiaryResponseDto toResponseDto(Penitentiary penitentiary) {
        PenitentiaryResponseDto responseDto = new PenitentiaryResponseDto();

        AddressResponse addressResponse = addressMapper.toResponse(penitentiary.getAddress());

        responseDto.setId(penitentiary.getId());
        responseDto.setName(penitentiary.getName());
        responseDto.setAddressResponse(addressResponse);

        return  responseDto;
    }
}
