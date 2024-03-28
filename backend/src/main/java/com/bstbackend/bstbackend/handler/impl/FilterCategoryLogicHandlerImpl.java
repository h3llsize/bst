package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.FilterCategoryDTO;
import com.bstbackend.bstbackend.handler.FilterCategoryLogicHandler;
import com.bstbackend.bstbackend.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterCategoryLogicHandlerImpl implements FilterCategoryLogicHandler {

    private final CategoryService categoryService;

    public FilterCategoryLogicHandlerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<FilterCategoryDTO> listFilters(Long categoryId, HttpServletRequest httpServletRequest) {
        return categoryService.listFilters(categoryId);
    }

}
