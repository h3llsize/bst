package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.FilterCategoryDTO;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@MappedSuperclass
public interface FilterCategoryLogicHandler {
    /**
     * Получение списка фильтров по id категории.
     * @param categoryId
     * @param httpServletRequest
     * @return
     */
    List<FilterCategoryDTO> listFilters(Long categoryId, HttpServletRequest httpServletRequest);

}
