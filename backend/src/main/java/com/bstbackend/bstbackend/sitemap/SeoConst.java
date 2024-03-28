package com.bstbackend.bstbackend.sitemap;

public class SeoConst {
    public static String robotsPattern(String url) {
        return String.format("Host: %s\n" +
                "\n" +
                "User-agent: *\n" +
                "Disallow: /*?\n" +
                "Sitemap: %s\n" +
                "\n" +
                "User-agent: Yandex\n" +
                "Disallow: /*?\n" +
                "Clean-param: session_id\n" +
                "Sitemap: %s", url, url + "/sitemap.xml", url + "/sitemap.xml");
    }
}
