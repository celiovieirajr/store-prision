package com.example.prision.modules.service;

import com.example.prision.modules.dto.PenitentiaryRequestDto;
import com.example.prision.modules.dto.PenitentiaryResponseDto;
import com.example.prision.modules.mapper.PenitentiaryMapper;
import com.example.prision.modules.model.Penitentiary;
import com.example.prision.modules.repository.PenitentiaryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PenitentiaryImplements implements IPenitentiaryService {

    private final PenitentiaryRepository penitentiaryRepository;
    private final PenitentiaryMapper penitentiaryMapper;

    public PenitentiaryImplements(PenitentiaryRepository penitentiaryRepository, PenitentiaryMapper penitentiaryMapper) {
        this.penitentiaryRepository = penitentiaryRepository;
        this.penitentiaryMapper = penitentiaryMapper;
    }

    public PenitentiaryResponseDto insertPenitentiary(PenitentiaryRequestDto penitentiaryRequestDto) {

        if (penitentiaryRequestDto.getName().isEmpty() || penitentiaryRequestDto.getName().length() < 3) {
            throw new IllegalArgumentException("Invalid name!");
        }

        Penitentiary model =  penitentiaryMapper.toModel(penitentiaryRequestDto);
        Penitentiary modelSaved =  penitentiaryRepository.save(model);

        return  penitentiaryMapper.toResponseDto(modelSaved);
    }

    public PenitentiaryResponseDto getPenitentiaryById(Long id) {

        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product id");
        }

        Penitentiary model = penitentiaryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penitentiary not found!"));
        return penitentiaryMapper.toResponseDto(model);
    }

    public List<PenitentiaryResponseDto> getAllPenitentiary() {
        return penitentiaryRepository.findAll().stream().map(penitentiaryMapper::toResponseDto).toList();
    }

    public PenitentiaryResponseDto updatedPenitentiaryById(Long id, PenitentiaryRequestDto penitentiaryRequestDto) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product id");
        }

        if (penitentiaryRequestDto.getName().isEmpty() || penitentiaryRequestDto.getName().length() <= 3) {
            throw new IllegalArgumentException("Invalid name!");
        }

        Penitentiary model = penitentiaryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penitentiary not found!"));

        model.setName(penitentiaryRequestDto.getName());

        Penitentiary modelAddres = penitentiaryMapper.toModel(penitentiaryRequestDto);
        model.setAddress(modelAddres.getAddress() == null ? null : modelAddres.getAddress());

        Penitentiary modelSaved = penitentiaryRepository.save(model);

        return  penitentiaryMapper.toResponseDto(modelSaved);
    }

    public void deletePenitentiaryById(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product id");
        }
        Penitentiary model = penitentiaryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penitentiary not found!"));

        penitentiaryRepository.delete(model);
    }
}
