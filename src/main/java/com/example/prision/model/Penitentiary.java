package com.example.prision.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "penitentiary")
@Data
public class Penitentiary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String UF;
    private String prisionUnit;
}
