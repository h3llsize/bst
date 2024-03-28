package com.bstbackend.bstbackend.dtos;

import lombok.Data;

@Data
public class FilterCategoryOptionDTO {
    private Long id;

    private String name;

    private boolean value = false;
}
