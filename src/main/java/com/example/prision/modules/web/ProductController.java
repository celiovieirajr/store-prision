package com.example.prision.modules.web;

import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> insertProductController(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto response =  productService.insertProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductByIdController(@PathVariable Long id) {
        ProductResponseDto response = productService.findProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProductsController() {
        List<ProductResponseDto> response = productService.findAllProducts();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<ProductResponseDto>> updateProductByIdController(@PathVariable Long id,
                                                                                @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto response =  productService.updateProduct(id, productRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByIdController(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
