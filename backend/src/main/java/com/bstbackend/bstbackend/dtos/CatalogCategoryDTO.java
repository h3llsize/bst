package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CatalogCategoryDTO {
    private Long id;

    private String name;

    private String slug;

    private String imagePath;

    private List<CatalogSubCategoryDTO> catalogSubCategories;
}
