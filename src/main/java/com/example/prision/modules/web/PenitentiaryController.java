package com.example.prision.modules.web;

import com.example.prision.modules.dto.PenitentiaryRequestDto;
import com.example.prision.modules.dto.PenitentiaryResponseDto;
import com.example.prision.modules.service.IPenitentiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/penitentiary")
public class PenitentiaryController {

    private final IPenitentiaryService penitentiaryService;

    public PenitentiaryController(IPenitentiaryService penitentiaryService) {
        this.penitentiaryService = penitentiaryService;
    }

    @PostMapping
    public ResponseEntity<PenitentiaryResponseDto> insertPenitentiary(@RequestBody PenitentiaryRequestDto penitentiaryRequestDto) {
        PenitentiaryResponseDto response = penitentiaryService.insertPenitentiary(penitentiaryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenitentiaryResponseDto> getPenitentiaryById(@PathVariable Long id) {
        PenitentiaryResponseDto response = penitentiaryService.getPenitentiaryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PenitentiaryResponseDto>> getAllPenitentiary() {
        List<PenitentiaryResponseDto> response = penitentiaryService.getAllPenitentiary();
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PenitentiaryResponseDto> updatedPenitentiaryById(@PathVariable Long id,
                                                                           @RequestBody PenitentiaryRequestDto penitentiaryRequestDto) {
        PenitentiaryResponseDto response = penitentiaryService.updatedPenitentiaryById(id, penitentiaryRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deletePenitentiaryById(@PathVariable Long id) {
        penitentiaryService.deletePenitentiaryById(id);
        return  ResponseEntity.noContent().build();
    }
}
