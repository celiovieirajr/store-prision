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

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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
    @PostMapping("/sale/{idSale}/itemSale")
    public ResponseEntity<ItemSaleResponseDto> insertItemSaleController(@RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                        @PathVariable Long idSale) {
        ItemSaleResponseDto responseDto = itemSaleService.insertItemSale(itemSaleRequestDto, idSale);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Find IntemSale by SALE and ItemSale", description = "Find IntemSale by SALE and ItemSale",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("sale/{idSale}/itemSale/{idItemSale}")
    public ResponseEntity<ItemSaleResponseDto> findByIdController(@PathVariable("id") Long idSale, Long idItemSale) {
        ItemSaleResponseDto response = itemSaleService.findById(idSale,  idItemSale);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Find all IntemSale by SALE and ItemSale", description = "Find all IntemSale by SALE and ItemSale",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/sale/{idSale}/itemSale")
    public ResponseEntity<List<ItemSaleResponseDto>> findAllController(@PathVariable Long saleId) {
        List<ItemSaleResponseDto> responseDto = itemSaleService.findAll(saleId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    @Operation(summary = "Updated IntemSale by SALE and ItemSale", description = "Updated IntemSale by SALE and ItemSale",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/sale/{idSale}/itemSale/{idItemSale}")
    public ResponseEntity<ItemSaleResponseDto> updatedByIdController(@PathVariable Long idSale,
                                                                     @RequestBody ItemSaleRequestDto itemSaleRequestDto,
                                                                     @PathVariable Long idItemSale) {
        ItemSaleResponseDto response = itemSaleService.updateItemById(idSale, itemSaleRequestDto,  idItemSale);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete IntemSale by SALE and ItemSale", description = "Delete IntemSale by SALE and ItemSale",
            tags = "ItemSale",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItemSaleRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @DeleteMapping("/sale/{idSale}/itemSale/idItemSale")
    public ResponseEntity<?> deleteByIdController(@PathVariable Long idSale, Long idItemSale) {
        itemSaleService.deleteById(idSale,  idItemSale);
        return ResponseEntity.noContent().build();
    }
}
