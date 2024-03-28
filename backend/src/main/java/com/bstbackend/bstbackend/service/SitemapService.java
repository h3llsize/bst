package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.entity.CatalogSubCategory;
import com.bstbackend.bstbackend.repo.CatalogCategoryRepository;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import com.bstbackend.bstbackend.sitemap.Sitemap;
import com.bstbackend.bstbackend.sitemap.SitemapObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SitemapService {


    private final CatalogCategoryRepository catalogCategoryRepository;
    private final CatalogSubcategoryRepository catalogSubcategoryRepository;

    private final CatalogProductsRepository catalogProductsRepository;


    public SitemapService(CatalogCategoryRepository catalogCategoryRepository, CatalogSubcategoryRepository catalogSubcategoryRepository, CatalogProductsRepository catalogProductsRepository) {
        this.catalogCategoryRepository = catalogCategoryRepository;
        this.catalogSubcategoryRepository = catalogSubcategoryRepository;
        this.catalogProductsRepository = catalogProductsRepository;
    }

    public String generateSitemap(String url) {
        Sitemap sitemap = new Sitemap(url);
        sitemap.addAll(this.buildCategoriesSitemap());
        sitemap.addAll(this.buildSubcategoriesSitemap());
        sitemap.addAll(this.buildProductsSitemap());

        return sitemap.build();
    }

    private List<SitemapObject> buildCategoriesSitemap() {
        return catalogCategoryRepository.categorySitemapRequest().stream().map(z -> new SitemapObject("/category/" + z, 0.8)).toList();
    }

    private List<SitemapObject> buildSubcategoriesSitemap() {
        return catalogSubcategoryRepository.categorySitemapRequest().stream().map(z -> new SitemapObject("/subcategory/" + z, 0.8)).toList();
    }

    private List<SitemapObject> buildProductsSitemap() {
        return catalogProductsRepository.findTop100ProductsPerSubcategory().stream().map(z -> new SitemapObject("/product/" + z, 0.4)).toList();
    }


}
