package com.example.prision.modules.repository;

import com.example.prision.modules.model.Penitentiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenitentiaryRepository extends JpaRepository<Penitentiary, Long> {
}
