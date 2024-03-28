package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FilterCategoryDTO {

    private Long id;

    private String name;

    private List<FilterCategoryOptionDTO> categoryOptions;

}
