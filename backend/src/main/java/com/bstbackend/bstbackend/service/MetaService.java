package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.dtos.MetaRequestDTO;
import com.bstbackend.bstbackend.meta.MetaHolder;
import com.bstbackend.bstbackend.meta.MetaTypes;
import org.springframework.stereotype.Component;


@Component
public class MetaService {

    private final MetaHolder metaHolder;

    public MetaService(MetaHolder metaHolder) {
        this.metaHolder = metaHolder;
    }

    public MetaDTO getMeta(MetaRequestDTO metaRequestDTO, String cityName) {
        return metaHolder.getMeta(MetaTypes.fromId(metaRequestDTO.getType()), metaRequestDTO.getSlug(), cityName);
    }
}
