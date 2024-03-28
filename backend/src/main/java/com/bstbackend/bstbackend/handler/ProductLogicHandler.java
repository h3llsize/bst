package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.CatalogProductDTO;
import com.bstbackend.bstbackend.dtos.CatalogProductWithOptionsDTO;
import com.bstbackend.bstbackend.dtos.ProductListSearchRequest;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

@MappedSuperclass
public interface ProductLogicHandler {
    /**
     * Получение списка продуктов, соответсвующих QUERY и CATEGORY_ID
     * @param searchRequest
     * @param httpServletRequest
     * @return
     */
    Page<CatalogProductDTO> productList(ProductListSearchRequest searchRequest, HttpServletRequest httpServletRequest);

    /**
     * Получение информации о продукте с фильтрами
     * @param slug
     * @param httpServletRequest
     * @return
     */
    CatalogProductWithOptionsDTO getById(String slug, HttpServletRequest httpServletRequest);

    /**
     * Получение информации о продукте
     * @param id
     * @param httpServletRequest
     * @return
     */
    CatalogProductDTO getById(Long id, HttpServletRequest httpServletRequest);

    /**
     * Получить похожие продукты
     * @param id
     * @param httpServletRequest
     * @return
     */
    List<CatalogProductDTO> similarItems(Long id, HttpServletRequest httpServletRequest);
}
