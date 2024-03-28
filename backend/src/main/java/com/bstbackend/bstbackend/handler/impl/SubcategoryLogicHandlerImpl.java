package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.CatalogSubCategoryDTO;
import com.bstbackend.bstbackend.dtos.CatalogSubcategoryItems;
import com.bstbackend.bstbackend.handler.SubcategoryLogicHandler;
import com.bstbackend.bstbackend.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubcategoryLogicHandlerImpl implements SubcategoryLogicHandler {

    private final CategoryService categoryService;

    public SubcategoryLogicHandlerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<CatalogSubcategoryItems> getCatalogSubCategory(String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategories from : " + httpServletRequest.getRemoteAddr());
        return categoryService.getCatalogSubCategoriesBySlug(slug, httpServletRequest.getHeader("city"));
    }

    @Override
    public List<CatalogSubcategoryItems> getCatalogSubCategoryAll(String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategories/all from : " + httpServletRequest.getRemoteAddr());
        return categoryService.getCatalogSubCategoriesBySlugAll(slug, httpServletRequest.getHeader("city"));
    }

    @Override
    public Page<CatalogSubCategoryDTO> listSubcategories(String query, Integer page, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategory/search from : " + httpServletRequest.getRemoteAddr());
        return categoryService.findSubcategories(query, page, httpServletRequest.getHeader("city"));
    }

    @Override
    public CatalogSubCategoryDTO getSubCategoryBySlug(String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategory/get from : " + httpServletRequest.getRemoteAddr());
        return categoryService.findCatalogSubCategoryBySlug(slug, httpServletRequest.getHeader("city"));
    }
}
