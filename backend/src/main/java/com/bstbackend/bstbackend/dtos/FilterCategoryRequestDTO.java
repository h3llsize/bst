package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FilterCategoryRequestDTO {
    private Long id;

    private List<Long> optionsId;
}
