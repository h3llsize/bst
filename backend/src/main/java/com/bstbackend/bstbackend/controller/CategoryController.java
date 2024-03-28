package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.*;
import com.bstbackend.bstbackend.handler.CategoryLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryLogicHandler categoryLogicHandler;

    public CategoryController(CategoryLogicHandler categoryLogicHandler) {
        this.categoryLogicHandler = categoryLogicHandler;
    }

    @GetMapping("/categories")
    public CatalogCategoryDTO getCatalogCategoryBySlug(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return categoryLogicHandler.getCatalogCategoryBySlug(slug, httpServletRequest);
    }

}
