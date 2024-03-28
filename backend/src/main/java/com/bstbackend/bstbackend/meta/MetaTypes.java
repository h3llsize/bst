package com.bstbackend.bstbackend.meta;

public enum MetaTypes {
    CATEGORY("category"), SUBCATEGORY("subcategory"),
    PRODUCT("product"), MAIN_CATEGORY("maincategory"), DEFAULT("default");
    private String id;

    MetaTypes(String id) {
        this.id = id;
    }

    public static MetaTypes fromId(String id) {
        for (MetaTypes value : MetaTypes.values()) {
            if(id.equals(value.id))
                return value;
        }
        return MetaTypes.DEFAULT;
    }
}
