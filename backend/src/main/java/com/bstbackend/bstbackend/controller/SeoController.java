package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.service.SitemapService;
import com.bstbackend.bstbackend.sitemap.SeoConst;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seo")
public class SeoController {
    private final SitemapService sitemapService;

    public SeoController(SitemapService sitemapService) {
        this.sitemapService = sitemapService;
    }
    @GetMapping("/sitemap.xml")
    public ResponseEntity<String> requestSitemap(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/sitemap.xml", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(sitemapService.buildLocality(url));
    }

    @GetMapping("/sitemap.category.xml")
    public ResponseEntity<String> getSitemapCategory(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/sitemap.category.xml", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(sitemapService.buildCategoriesSitemap(url));
    }

    @GetMapping("/sitemap.subcategory.xml")
    private ResponseEntity<String> getSitemapSubcategory(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/sitemap.subcategory.xml", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(sitemapService.buildSubcategoriesSitemap(url));
    }

    @GetMapping("/sitemap.misc.xml")
    private ResponseEntity<String> getSitemapMisc(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/sitemap.misc.xml", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(sitemapService.buildMiscSitemap(url));
    }

    @GetMapping("/sitemap.product.xml")
    private ResponseEntity<String> getSitemapProducts(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/sitemap.product.xml", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(sitemapService.buildProductsSitemap(url));
    }

    @GetMapping("/robots.txt")
    private ResponseEntity<String> requestRobots(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/robots.txt", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "text/plain")
                .body(SeoConst.robotsPattern(url));
    }
}
