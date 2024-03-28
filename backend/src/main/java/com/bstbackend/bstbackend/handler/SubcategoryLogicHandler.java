package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.CatalogSubCategoryDTO;
import com.bstbackend.bstbackend.dtos.CatalogSubcategoryItems;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

@MappedSuperclass
public interface SubcategoryLogicHandler {
    /**
     * Получение всех подкатегорий, привязанных к основной категории
     *
     * @param slug CatalogSubcategory
     * @param httpServletRequest
     * @return
     */
    List<CatalogSubcategoryItems> getCatalogSubCategory(String slug, HttpServletRequest httpServletRequest);

    /** Получение всех подкатегорий, привязанных к основной категории
     * TODO: Два одиннаковых запроса, разобраться!
     *
     * @param slug CatalogCategory
     * @param httpServletRequest
     * @return
     */
    List<CatalogSubcategoryItems> getCatalogSubCategoryAll(String slug, HttpServletRequest httpServletRequest);

    /**
     * Получение подкатегорий по query.
     * Возвращаются только подкатегории, содержащие в себе товары, подходящие под query
     *
     * @param query
     * @param page
     * @param httpServletRequest
     * @return
     */
    Page<CatalogSubCategoryDTO> listSubcategories(String query, Integer page, HttpServletRequest httpServletRequest);

    /**
     * Получение информации о подкатегории
     *
     * @param slug
     * @param httpServletRequest
     * @return
     */
    CatalogSubCategoryDTO getSubCategoryBySlug(String slug, HttpServletRequest httpServletRequest);
}
