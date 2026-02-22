package com.example.prision.modules.service;

import com.example.prision.modules.dto.PenitentiaryRequestDto;
import com.example.prision.modules.dto.PenitentiaryResponseDto;

import java.util.List;

public interface IPenitentiaryService {
    PenitentiaryResponseDto insertPenitentiary(PenitentiaryRequestDto penitentiaryRequestDto);
    PenitentiaryResponseDto getPenitentiaryById(Long id);
    List<PenitentiaryResponseDto> getAllPenitentiary();
    PenitentiaryResponseDto updatedPenitentiaryById(Long id, PenitentiaryRequestDto penitentiaryRequestDto);
    void deletePenitentiaryById(Long id);
}
