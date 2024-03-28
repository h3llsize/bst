package com.bstbackend.bstbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "catalog_category")
@Getter
@Setter
public class CatalogCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NonNull
    private String name;

    private String flag;

    private String imagePath;

    @OneToMany(mappedBy = "catalogCategory")
    private List<CatalogSubCategory> catalogSubCategories;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_catalog_category_id")
    private MainCatalogCategory mainCatalogCategory;
}
