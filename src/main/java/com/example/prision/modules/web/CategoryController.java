package com.example.prision.modules.web;

import com.example.prision.modules.dto.CategoryRequestDto;
import com.example.prision.modules.dto.CategoryResponseDto;
import com.example.prision.modules.dto.ItemSaleRequestDto;
import com.example.prision.modules.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/category")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Insert category", description = "Insert category",
            tags = "Category",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = CategoryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PostMapping
    public ResponseEntity<CategoryResponseDto> insertCategoryController(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.insertCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDto);
    }

    @Operation(summary = "Find category by Id", description = "Find category by Id",
            tags = "Category",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CategoryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryByIdController(@PathVariable Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.findCategoryById(id);
        return ResponseEntity.ok().body(categoryResponseDto);
    }

    @Operation(summary = "Find all category by Id", description = "Find all category by Id",
            tags = "Category",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CategoryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAllCategoriesController() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.findAllCategories();
        return ResponseEntity.ok().body(categoryResponseDtoList);
    }

    @Operation(summary = "Updated category by Id", description = "Updated category by Id",
            tags = "Category",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = CategoryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id,
                                                              @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto response = categoryService.updateCategory(id, categoryRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete category by Id", description = "Delete category by Id",
            tags = "Category",
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = CategoryRequestDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return  ResponseEntity.noContent().build();
    }
}
