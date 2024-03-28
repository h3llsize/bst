package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.FilterCategoryDTO;
import com.bstbackend.bstbackend.handler.FilterCategoryLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class FilterController {

    private final FilterCategoryLogicHandler filterCategoryLogicHandler;

    public FilterController(FilterCategoryLogicHandler filterCategoryLogicHandler) {
        this.filterCategoryLogicHandler = filterCategoryLogicHandler;
    }

    @GetMapping("/filters")
    public List<FilterCategoryDTO> listFilters(@Param("id") Long categoryId, HttpServletRequest httpServletRequest) {
        return filterCategoryLogicHandler.listFilters(categoryId, httpServletRequest);
    }

}
