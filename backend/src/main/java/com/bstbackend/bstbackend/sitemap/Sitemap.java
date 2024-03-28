package com.bstbackend.bstbackend.sitemap;

import java.util.ArrayList;
import java.util.List;

public class Sitemap {

    private String url;

    private List<SitemapObject> sitemapObjects = new ArrayList<>();

    public Sitemap(String url) {
        this.url = url;
        sitemapObjects.addAll(DefaultSitemaps.sitemapObjects);
    }

    public void add(SitemapObject sitemapObject) {
        sitemapObjects.add(sitemapObject);
    }

    public void addAll(List<SitemapObject> sitemapObject) {
        sitemapObjects.addAll(sitemapObject);
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">");
        stringBuilder.append("\n");
        for (SitemapObject sitemapObject : sitemapObjects) {
            stringBuilder.append(sitemapObject.build(url));
        }
        stringBuilder.append("</urlset>");
        return stringBuilder.toString();
    }
}
