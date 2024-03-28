package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.CatalogCategoryDTO;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;

@MappedSuperclass
public interface CategoryLogicHandler {
    /**
     * Получение информации о категории
     * @param slug
     * @param httpServletRequest
     * @return
     */
    CatalogCategoryDTO getCatalogCategoryBySlug(String slug, HttpServletRequest httpServletRequest);
}
