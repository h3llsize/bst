package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.service.SitemapService;
import com.bstbackend.bstbackend.sitemap.SeoConst;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {


    private final SitemapService sitemapService;

    public ApiController(SitemapService sitemapService) {
        this.sitemapService = sitemapService;
    }

    @GetMapping("/sitemap.xml")
    private ResponseEntity<String> requestSitemap(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString().replaceAll("/sitemap.xml", "")
                .replaceAll("/api", "").replaceAll("http", "https");

        return ResponseEntity.ok()
                .header("Content-Type", "application/xml")
                .body(sitemapService.generateSitemap(url));
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
