package com.example.prision.modules.web;

import com.example.prision.modules.dto.PenitentiaryRequestDto;
import com.example.prision.modules.dto.PenitentiaryResponseDto;
import com.example.prision.modules.dto.SaleRequestDto;
import com.example.prision.modules.service.IPenitentiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Insert penitentiary", description = "Insert penitentiary",
            tags = "Penitentiary",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = PenitentiaryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PostMapping
    public ResponseEntity<PenitentiaryResponseDto> insertPenitentiary(@RequestBody PenitentiaryRequestDto penitentiaryRequestDto) {
        PenitentiaryResponseDto response = penitentiaryService.insertPenitentiary(penitentiaryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Find penitentiary by Id", description = "Find penitentiary by Id",
            tags = "Penitentiary",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PenitentiaryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/{id}")
    public ResponseEntity<PenitentiaryResponseDto> getPenitentiaryById(@PathVariable Long id) {
        PenitentiaryResponseDto response = penitentiaryService.getPenitentiaryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Find all penitentiary by Id", description = "Find all penitentiary by Id",
            tags = "Penitentiary",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PenitentiaryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping
    public ResponseEntity<List<PenitentiaryResponseDto>> getAllPenitentiary() {
        List<PenitentiaryResponseDto> response = penitentiaryService.getAllPenitentiary();
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Updated penitentiary by Id", description = "Updated penitentiary by Id",
            tags = "Penitentiary",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = PenitentiaryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<PenitentiaryResponseDto> updatedPenitentiaryById(@PathVariable Long id,
                                                                           @RequestBody PenitentiaryRequestDto penitentiaryRequestDto) {
        PenitentiaryResponseDto response = penitentiaryService.updatedPenitentiaryById(id, penitentiaryRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete penitentiary by Id", description = "Delete penitentiary by Id",
            tags = "Penitentiary",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = PenitentiaryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deletePenitentiaryById(@PathVariable Long id) {
        penitentiaryService.deletePenitentiaryById(id);
        return  ResponseEntity.noContent().build();
    }
}
