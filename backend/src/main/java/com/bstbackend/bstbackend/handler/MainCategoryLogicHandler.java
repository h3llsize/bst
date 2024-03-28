package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.CatalogCategoryDTO;
import com.bstbackend.bstbackend.dtos.MainCatalogCategoryDTO;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@MappedSuperclass
public interface MainCategoryLogicHandler {
    /**
     * Получаем все основные категории
     *
     * @param httpServletRequest
     * @return List<MainCatalogCategoryDTO>
     */
    List<MainCatalogCategoryDTO> getAllMainCatalogCategories(HttpServletRequest httpServletRequest);

    /**
     * Получение информации о основной категории
     *
     * @param slug
     * @param httpServletRequest
     * @return MainCatalogCategoryDTO
     */
    MainCatalogCategoryDTO getMainCatalogCategory(String slug, HttpServletRequest httpServletRequest);


    /**
     * Получение категорий, привязанных к основной категории
     *
     * @param slug
     * @param httpServletRequest
     * @return List<CatalogCategoryDTO>
     */
    List<CatalogCategoryDTO> getCatalogCategoriesBySlug(String slug, HttpServletRequest httpServletRequest);
}
