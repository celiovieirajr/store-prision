package com.example.prision.modules.web;

import com.example.prision.modules.dto.ProductRequestDto;
import com.example.prision.modules.dto.ProductResponseDto;
import com.example.prision.modules.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> insertProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto response =  productService.insertProduct(productRequestDto);
        return ResponseEntity.ok(response);
    }
}
