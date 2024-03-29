package com.bstbackend.bstbackend.sitemap;

import java.util.ArrayList;
import java.util.List;

public class SitemapIndex {
    private List<SitemapLocalityObject> sitemapLocalityObjects = new ArrayList<>();

    public SitemapIndex addLocality(SitemapLocalityObject sitemapLocalityObject) {
        sitemapLocalityObjects.add(sitemapLocalityObject);

        return this;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<sitemapindex xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/siteindex.xsd\">");
        for (SitemapLocalityObject sitemapLocalityObject : sitemapLocalityObjects) {
            stringBuilder.append("\n");
            stringBuilder.append(sitemapLocalityObject.build());
        }
        stringBuilder.append("</sitemapindex>");
        return stringBuilder.toString();
    }
}
