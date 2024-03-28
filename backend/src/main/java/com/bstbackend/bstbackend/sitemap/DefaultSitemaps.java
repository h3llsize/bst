package com.bstbackend.bstbackend.sitemap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DefaultSitemaps {
    public static List<SitemapObject> sitemapObjects = new ArrayList<>();

    static {
        sitemapObjects.add(new SitemapObject("", 1));
    }
}
