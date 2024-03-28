package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CatalogSubcategoryItems {
    private Long id;

    private String slug;

    private String name;

    private List<CatalogProductDTO> products;
}
