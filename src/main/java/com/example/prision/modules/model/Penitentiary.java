package com.example.prision.modules.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "penitentiary")
@Data
public class Penitentiary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Address address;
}
