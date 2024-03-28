package com.bstbackend.bstbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "catalog_subcategory")
@Getter
@Setter
public class CatalogSubCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String flag;

    @NonNull
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String manufacturing;

    private String advantages;

    private String range;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "catalog_id")
    private CatalogCategory catalogCategory;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "catalogCategory")
    private List<CatalogProduct> products;


}
