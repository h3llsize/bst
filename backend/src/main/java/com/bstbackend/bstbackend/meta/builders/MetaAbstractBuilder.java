package com.bstbackend.bstbackend.meta.builders;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.dtos.MetaRequestDTO;
import com.bstbackend.bstbackend.meta.MetaBuilder;
import com.bstbackend.bstbackend.meta.MetaConst;
import com.bstbackend.bstbackend.meta.MetaModel;
import com.bstbackend.bstbackend.meta.MetaTypes;
import com.bstbackend.bstbackend.service.CityService;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MetaAbstractBuilder implements MetaBuilder {
    private final CityService cityService;

    protected MetaAbstractBuilder(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public MetaDTO buildMetaDto(String slug, String cityName) {
        return build(buildMeta(slug, cityService.getDeclinsionCityName(cityName)), cityName);
    }

    public abstract MetaModel buildMeta(String slug, String cityName);

    private MetaDTO build(MetaModel metaModel, String cityName)
    {
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.getOtherDTO().setTitle(metaModel.getTitle());
        metaDTO.getOtherDTO().setKeywords(metaModel.getKeywords());
        metaDTO.getOtherDTO().setDescription(metaModel.getDesc());

        metaDTO.getOpenGraph().setDescription(metaModel.getDesc());
        metaDTO.getOpenGraph().setTitle(metaModel.getTitle());
        metaDTO.getOpenGraph().setType(MetaConst.OPENGRAPH_TYPE);
        metaDTO.getOpenGraph().setLocale(MetaConst.OPENGRAPH_LOCALE);
        metaDTO.getOpenGraph().setLocality(cityService.getDefaultName(cityName));
        metaDTO.getOpenGraph().setSiteName(MetaConst.OPENGRAPH_SITENAME);

        return metaDTO;
    }
}
