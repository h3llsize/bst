package com.bstbackend.bstbackend.dtos;

import lombok.Data;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Data
public class ProductListSearchRequest {
    private Long categoryId;
    private String query;
    private Integer page;
    private String slug;
    private List<FilterCategoryRequestDTO> filters;

    public String getQueryNonNull() {
        if(query == null)
            return "";

        return query;
    }
}
