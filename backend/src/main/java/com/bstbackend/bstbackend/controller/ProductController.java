package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.CatalogProductDTO;
import com.bstbackend.bstbackend.dtos.CatalogProductWithOptionsDTO;
import com.bstbackend.bstbackend.dtos.ProductListSearchRequest;
import com.bstbackend.bstbackend.handler.ProductLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ProductController {

    private final ProductLogicHandler productLogicHandler;

    public ProductController(ProductLogicHandler productLogicHandler) {
        this.productLogicHandler = productLogicHandler;
    }

    @PostMapping("/products-list")
    public Page<CatalogProductDTO> productList(@RequestBody ProductListSearchRequest searchRequest, HttpServletRequest httpServletRequest) {
        return productLogicHandler.productList(searchRequest, httpServletRequest);
    }
    @GetMapping("/product")
    public CatalogProductWithOptionsDTO getById(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
        return productLogicHandler.getById(slug, httpServletRequest);
    }

    @GetMapping("/product/get")
    public CatalogProductDTO getById(@Param("id") Long id, HttpServletRequest httpServletRequest) {
        return productLogicHandler.getById(id, httpServletRequest);
    }

    @GetMapping("/product/similar")
    public List<CatalogProductDTO> similarItems(@Param("id") Long id, HttpServletRequest httpServletRequest) {
        return productLogicHandler.similarItems(id, httpServletRequest);
    }
}
