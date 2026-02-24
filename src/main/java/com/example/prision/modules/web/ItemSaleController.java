package com.example.prision.modules.web;

import com.example.prision.modules.dto.ItemSaleRequestDto;
import com.example.prision.modules.dto.ItemSaleResponseDto;
import com.example.prision.modules.service.IItemSaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/itemSale")
public class ItemSaleController {

    private final IItemSaleService itemSaleService;

    public ItemSaleController(IItemSaleService itemSaleService) {
        this.itemSaleService = itemSaleService;
    }


    @Operation(summary = "Insert IntemSale by SALE", description = "Insert IntemSale",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PostMapping()
    public ResponseEntity<ItemSaleResponseDto> insertItemSaleController(@RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                        @PathVariable("saleId") Long saleId) {
        ItemSaleResponseDto responseDto = itemSaleService.insertItemSale(itemSaleRequestDto, saleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
