package com.bstbackend.bstbackend.sitemap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SitemapObject {
    private final String path;

    private final String lastMod = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private final String changeFreq = "weekly";

    private final double priority;

    public SitemapObject(String path, double priority) {
        this.path = path;
        this.priority = priority;
    }

    public String build(String url) {
        return String.format(Locale.US,"<url>\n" +
                "   <loc>%s</loc>\n" +
                "   <lastmod>%s</lastmod>\n" +
                "   <changefreq>%s</changefreq>\n" +
                "   <priority>%.1f</priority>\n" +
                "</url>\n",url + path, lastMod, changeFreq, priority);
    }
}
