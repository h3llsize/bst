package com.bstbackend.bstbackend.sitemap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SitemapLocalityObject {
    private final String loc;

    private final String lastMod = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public SitemapLocalityObject(String loc) {
        this.loc = loc;
    }

    public String build() {
        return String.format("<sitemap>\n" +
                "<loc>%s</loc>\n" +
                "<lastmod>%s</lastmod>\n" +
                "</sitemap>", loc, lastMod);
    }
}
