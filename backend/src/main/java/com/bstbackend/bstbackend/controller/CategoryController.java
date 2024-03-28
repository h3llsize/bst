package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.*;
import com.bstbackend.bstbackend.service.CategoryService;
import com.bstbackend.bstbackend.service.ProductService;
import com.bstbackend.bstbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService catalogService;

    private final ProductService productService;
    private final UserService userService;

    public CategoryController(CategoryService catalogService, ProductService productService, UserService userService) {
        this.catalogService = catalogService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/main-categories")
    public List<MainCatalogCategoryDTO> getAllMainCatalogCategories(HttpServletRequest httpServletRequest) {
//        log.debug("Request to /main-categories from : " + httpServletRequest.getRemoteAddr());
        return catalogService.findAllMainCategories();
    }

    @GetMapping("/main-category/get")
    public MainCatalogCategoryDTO getMainCatalogCategory(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /main-categories from : " + httpServletRequest.getRemoteAddr());
        return catalogService.getMainCatalogCategory(slug, httpServletRequest.getHeader("city"));
    }

    @GetMapping("/main-categories/get")
    public List<CatalogCategoryDTO> getCatalogCategoriesBySlug(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /main-categories/get from : " + httpServletRequest.getRemoteAddr());
        return catalogService.getAllCatalogCategoryBySlug(slug);
    }
    
    @GetMapping("/categories")
    public CatalogCategoryDTO getCatalogCategoryBySlug(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /categories from : " + httpServletRequest.getRemoteAddr());
        return catalogService.getCatalogCategoryBySlug(slug, httpServletRequest.getHeader("city"));
    }

    @GetMapping("/subcategories")
    public List<CatalogSubcategoryItems> getCatalogSubCategory(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategories from : " + httpServletRequest.getRemoteAddr());
        return catalogService.getCatalogSubCategoriesBySlug(slug, httpServletRequest.getHeader("city"));
    }

    @GetMapping("/subcategories/all")
    public List<CatalogSubcategoryItems> getCatalogSubCategoryAll(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategories/all from : " + httpServletRequest.getRemoteAddr());
        return catalogService.getCatalogSubCategoriesBySlugAll(slug, httpServletRequest.getHeader("city"));
    }

    @GetMapping("/subcategory/search")
    public Page<CatalogSubCategoryDTO> listSubcategories(@Param("query") String query, Integer page, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategory/search from : " + httpServletRequest.getRemoteAddr());
        return catalogService.findSubcategories(query, page, httpServletRequest.getHeader("city"));
    }

    @GetMapping("/subcategory/get")
    public CatalogSubCategoryDTO getSubCategoryBySlug(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /subcategory/get from : " + httpServletRequest.getRemoteAddr());
        return catalogService.findCatalogSubCategoryBySlug(slug, httpServletRequest.getHeader("city"));
    }

    @PostMapping("/products-list")
    public Page<CatalogProductDTO> productList(@RequestBody ProductListSearchRequest searchRequest, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /products-list from : " + httpServletRequest.getRemoteAddr());
        return productService.listProducts(searchRequest);
    }
    @GetMapping("/product")
    public CatalogProductWithOptionsDTO getById(@Param("slug") String slug, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /product from : " + httpServletRequest.getRemoteAddr());
        return productService.getProductsBySlug(slug, httpServletRequest.getHeader("city"));
    }

    @GetMapping("/product/get")
    public CatalogProductDTO getById(@Param("id") Long id, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /product/get from : " + httpServletRequest.getRemoteAddr());
        return productService.getProductById(id);
    }

    @GetMapping("/filters")
    public List<FilterCategoryDTO> listFilters(@Param("id") Long categoryId, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /filters from : " + httpServletRequest.getRemoteAddr());
        return catalogService.listFilters(categoryId);
    }

    @GetMapping("/product/similar")
    public List<CatalogProductDTO> similarItems(@Param("id") Long id, HttpServletRequest httpServletRequest) {
//        log.debug("Request to /product/similar from : " + httpServletRequest.getRemoteAddr());
        return productService.randomList(id);
    }

    @PostMapping("/request")
    public ResponseEntity<String> addRequest(@RequestBody UserRequestDTO userRequestDTO, HttpServletRequest httpServletRequest) throws EmailException {
//        log.debug("Request to /request from : " + httpServletRequest.getRemoteAddr());
        userService.saveUserRequest(userRequestDTO);

        return ResponseEntity.ok("Success");
    }

}
