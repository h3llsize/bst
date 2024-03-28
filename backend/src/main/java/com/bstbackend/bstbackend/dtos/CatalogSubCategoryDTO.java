package com.bstbackend.bstbackend.dtos;

import lombok.Data;

@Data
public class CatalogSubCategoryDTO {
    private Long id;

    private String slug;

    private String name;

    private String imagePath;
}
