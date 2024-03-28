package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MainCatalogCategoryDTO {
    private String name;

    private Long id;

    private String slug;

    private List<CatalogCategoryDTO> categories;
}
