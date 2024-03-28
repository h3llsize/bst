package com.bstbackend.bstbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "catalog_product")
@Getter
@Setter
public class CatalogProduct {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NonNull
    private String name;

    private String shortName;

    @NonNull
    private Long price;

    @Column(unique = true)
    private String slug;

    private Boolean visible = false;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "catalog_id")
    private CatalogSubCategory catalogCategory;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<FilterCategoryOption> categoryOptions;
}
