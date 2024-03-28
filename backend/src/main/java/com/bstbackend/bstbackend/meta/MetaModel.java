package com.bstbackend.bstbackend.meta;

public class MetaModel {
    private final String desc;

    private final String keywords;

    private final String title;
    public MetaModel(String title, String desc, String keywords) {
        this.title = title;
        this.desc = desc;
        this.keywords = keywords;
    }

    public String getDesc() {
        return desc;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getTitle() {
        return title;
    }
}
