package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CatalogProductWithOptionsDTO {
    private Long id;

    private String name;

    private Long price;

    private Long categoryId;

    private List<FilterCategoryOptionParamDTO> options;

    private String pathToImage;

    private String description;

    private String manufacturing;

    private String advantages;

    private String range;
}
