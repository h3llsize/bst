package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.CatalogCategoryDTO;
import com.bstbackend.bstbackend.handler.CategoryLogicHandler;
import com.bstbackend.bstbackend.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryLogicHandlerImpl implements CategoryLogicHandler {

    private final CategoryService categoryService;

    public CategoryLogicHandlerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Override
    public CatalogCategoryDTO getCatalogCategoryBySlug(String slug, HttpServletRequest httpServletRequest) {
        return categoryService.getCatalogCategoryBySlug(slug, httpServletRequest.getHeader("city"));
    }
}
