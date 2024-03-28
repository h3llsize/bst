package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.CatalogProductDTO;
import com.bstbackend.bstbackend.dtos.CatalogProductWithOptionsDTO;
import com.bstbackend.bstbackend.dtos.ProductListSearchRequest;
import com.bstbackend.bstbackend.handler.ProductLogicHandler;
import com.bstbackend.bstbackend.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductLogicHandlerImpl implements ProductLogicHandler {

    private final ProductService productService;

    public ProductLogicHandlerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Page<CatalogProductDTO> productList(ProductListSearchRequest searchRequest, HttpServletRequest httpServletRequest) {
        return productService.listProducts(searchRequest);
    }
    @Override
    public CatalogProductWithOptionsDTO getById(String slug, HttpServletRequest httpServletRequest) {
        return productService.getProductsBySlug(slug, httpServletRequest.getHeader("city"));
    }
    @Override
    public CatalogProductDTO getById(Long id, HttpServletRequest httpServletRequest) {
        return productService.getProductById(id);
    }
    @Override
    public List<CatalogProductDTO> similarItems(@Param("id") Long id, HttpServletRequest httpServletRequest) {
        return productService.randomList(id);
    }

}
