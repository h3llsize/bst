package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.entity.CatalogSubCategory;
import com.bstbackend.bstbackend.repo.CatalogCategoryRepository;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import com.bstbackend.bstbackend.sitemap.*;
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

    public String buildLocality(String url) {
        return new SitemapIndex()
                .addLocality(new SitemapLocalityObject(url + "/sitemap.category.xml"))
                .addLocality(new SitemapLocalityObject(url + "/sitemap.subcategory.xml"))
                .addLocality(new SitemapLocalityObject(url + "/sitemap.product.xml"))
                .addLocality(new SitemapLocalityObject(url + "/sitemap.misc.xml"))
                .build();
    }

    public String buildMiscSitemap(String url) {
        Sitemap sitemap = new Sitemap(url);
        for (SitemapObject sitemapObject : DefaultSitemaps.sitemapObjects) {
            sitemap.add(sitemapObject);
        }
        return sitemap.build();
    }

    public String buildCategoriesSitemap(String url) {
        Sitemap sitemap = new Sitemap(url);
        sitemap.addAll(catalogCategoryRepository.categorySitemapRequest().stream().map(z -> new SitemapObject("/category/" + z, 0.8)).toList());
        return sitemap.build();
    }

    public String buildSubcategoriesSitemap(String url) {
        Sitemap sitemap = new Sitemap(url);
        sitemap.addAll(catalogSubcategoryRepository.categorySitemapRequest().stream().map(z -> new SitemapObject("/subcategory/" + z, 0.8)).toList());
        return sitemap.build();
    }

    public String buildProductsSitemap(String url) {
        Sitemap sitemap = new Sitemap(url);
        sitemap.addAll(catalogProductsRepository.findTop100ProductsPerSubcategory().stream().map(z -> new SitemapObject("/product/" + z, 0.4)).toList());
        return sitemap.build();
    }


}
