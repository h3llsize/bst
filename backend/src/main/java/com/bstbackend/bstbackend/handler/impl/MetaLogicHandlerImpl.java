package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.dtos.MetaRequestDTO;
import com.bstbackend.bstbackend.handler.MetaLogicHandler;
import com.bstbackend.bstbackend.service.MetaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class MetaLogicHandlerImpl implements MetaLogicHandler {
    private final MetaService metaService;

    public MetaLogicHandlerImpl(MetaService metaService) {
        this.metaService = metaService;
    }

    @Override
    public MetaDTO getMetaDTO(MetaRequestDTO metaRequestDTO, HttpServletRequest httpServletRequest) {
        return metaService.getMeta(metaRequestDTO, httpServletRequest.getHeader("city"));
    }
}
