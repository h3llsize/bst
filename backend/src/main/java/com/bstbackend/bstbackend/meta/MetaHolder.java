package com.bstbackend.bstbackend.meta;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.meta.MetaBuilder;
import com.bstbackend.bstbackend.meta.MetaTypes;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class MetaHolder {

    private final HashMap<MetaTypes, MetaBuilder> metaBuilderHashMap = new HashMap<>();

    public MetaHolder(List<MetaBuilder> metaBuilders) {
        for (MetaBuilder metaBuilder : metaBuilders) {
            metaBuilderHashMap.put(metaBuilder.type(), metaBuilder);
        }
    }

    public MetaDTO getMeta(MetaTypes metaTypes, String slug, String cityName) {
        return metaBuilderHashMap.get(metaTypes).buildMetaDto(slug, cityName);
    }

}
