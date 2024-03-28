package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.CatalogCategoryDTO;
import com.bstbackend.bstbackend.dtos.MainCatalogCategoryDTO;
import com.bstbackend.bstbackend.handler.MainCategoryLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class MainCategoryController {

    private final MainCategoryLogicHandler mainCategoryLogicHandler;

    public MainCategoryController(MainCategoryLogicHandler mainCategoryLogicHandler) {
        this.mainCategoryLogicHandler = mainCategoryLogicHandler;
    }

    @GetMapping("/main-categories")
    public List<MainCatalogCategoryDTO> getAllMainCatalogCategories(HttpServletRequest httpServletRequest) {
        return mainCategoryLogicHandler.getAllMainCatalogCategories(httpServletRequest);
    }

    @GetMapping("/main-category/get")
    public MainCatalogCategoryDTO getMainCatalogCategory(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return mainCategoryLogicHandler.getMainCatalogCategory(slug, httpServletRequest);
    }

    @GetMapping("/main-categories/get")
    public List<CatalogCategoryDTO> getCatalogCategoriesBySlug(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return mainCategoryLogicHandler.getCatalogCategoriesBySlug(slug, httpServletRequest);
    }
}
