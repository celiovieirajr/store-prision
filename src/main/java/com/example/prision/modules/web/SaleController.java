package com.example.prision.modules.web;

import com.example.prision.modules.dto.CategoryRequestDto;
import com.example.prision.modules.dto.SaleRequestDto;
import com.example.prision.modules.dto.SaleResponseDto;
import com.example.prision.modules.service.ISaleService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sale")
public class SaleController {

    private final ISaleService saleService;

    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @Operation(summary = "Insert sale", description = "Insert sale",
            tags = "Sale",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = SaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PostMapping()
    public ResponseEntity<SaleResponseDto> insertSale(@Valid @RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto response = saleService.createSale(saleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Find sale by Id", description = "Find sale by Id",
            tags = "Sale",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> findByIdController(@PathVariable Long id) {
        SaleResponseDto response = saleService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all sale by Id", description = "Find all sale by Id",
            tags = "Sale",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> findAllController() {
        List<SaleResponseDto> response = saleService.findAll();
        return  ResponseEntity.ok(response);
    }

    @Operation(summary = "Updated sale by Id", description = "Updated sale by Id",
            tags = "Sale",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = SaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/{idSale}")
    public ResponseEntity<SaleResponseDto> updateById(@PathVariable Long idSale,
                                                      @Valid @RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto response = saleService.updatedSale(idSale, saleRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete sale by Id", description = "Find sale by Id",
            tags = "Sale",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = SaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @DeleteMapping("/{idSale}")
    public ResponseEntity<SaleResponseDto> deleteById(@PathVariable Long idSale){
        saleService.deleteById(idSale);
        return ResponseEntity.noContent().build();
    }
}
