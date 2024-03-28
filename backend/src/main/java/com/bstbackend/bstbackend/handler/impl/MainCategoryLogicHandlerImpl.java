package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.CatalogCategoryDTO;
import com.bstbackend.bstbackend.dtos.MainCatalogCategoryDTO;
import com.bstbackend.bstbackend.handler.MainCategoryLogicHandler;
import com.bstbackend.bstbackend.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainCategoryLogicHandlerImpl implements MainCategoryLogicHandler {

    private final CategoryService categoryService;

    public MainCategoryLogicHandlerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<MainCatalogCategoryDTO> getAllMainCatalogCategories(HttpServletRequest httpServletRequest) {
        return categoryService.findAllMainCategories();
    }

    @Override
    public MainCatalogCategoryDTO getMainCatalogCategory(String slug, HttpServletRequest httpServletRequest) {
        return categoryService.getMainCatalogCategory(slug, httpServletRequest.getHeader("city"));
    }

    @Override
    public List<CatalogCategoryDTO> getCatalogCategoriesBySlug(String slug, HttpServletRequest httpServletRequest) {
        return categoryService.getAllCatalogCategoryBySlug(slug);
    }
}
