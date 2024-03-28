package com.bstbackend.bstbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "main_catalog_category")
@Getter
@Setter
public class MainCatalogCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Column(unique = true)
    private String slug;

    @OneToMany(mappedBy = "mainCatalogCategory")
    private List<CatalogCategory> catalogCategories;
}
