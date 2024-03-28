package com.bstbackend.bstbackend.meta;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public interface MetaBuilder {
    MetaTypes type();

    MetaDTO buildMetaDto(String slug, String cityName);
}
