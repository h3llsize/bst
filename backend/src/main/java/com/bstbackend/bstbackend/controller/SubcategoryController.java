package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.CatalogSubCategoryDTO;
import com.bstbackend.bstbackend.dtos.CatalogSubcategoryItems;
import com.bstbackend.bstbackend.handler.SubcategoryLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class SubcategoryController {
    private final SubcategoryLogicHandler subcategoryLogicHandler;

    public SubcategoryController(SubcategoryLogicHandler subcategoryLogicHandler) {
        this.subcategoryLogicHandler = subcategoryLogicHandler;
    }

    @GetMapping("/subcategories")
    public List<CatalogSubcategoryItems> getCatalogSubCategory(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return subcategoryLogicHandler.getCatalogSubCategory(slug, httpServletRequest);
    }

    @GetMapping("/subcategories/all")
    public List<CatalogSubcategoryItems> getCatalogSubCategoryAll(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return subcategoryLogicHandler.getCatalogSubCategoryAll(slug, httpServletRequest);
    }

    @GetMapping("/subcategory/search")
    public Page<CatalogSubCategoryDTO> listSubcategories(@Param("query") String query, Integer page, HttpServletRequest httpServletRequest) {
        return subcategoryLogicHandler.listSubcategories(query, page, httpServletRequest);
    }

    @GetMapping("/subcategory/get")
    public CatalogSubCategoryDTO getSubCategoryBySlug(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return subcategoryLogicHandler.getSubCategoryBySlug(slug, httpServletRequest);
    }

    @GetMapping("/subcategory/start")
    public Page<CatalogSubcategoryItems> listSubcategoriesStart(@Param("query") String query, Integer page, HttpServletRequest httpServletRequest) {
        return subcategoryLogicHandler.listSubcategoriesStart(query, page, httpServletRequest);
    }
}
