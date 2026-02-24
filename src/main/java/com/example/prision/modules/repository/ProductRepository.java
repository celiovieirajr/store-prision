package com.example.prision.modules.repository;

import com.example.prision.modules.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
