package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.dtos.MetaRequestDTO;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;

@MappedSuperclass
public interface MetaLogicHandler {
    /**
     * Получение метаданных для индексация сайта
     * @param metaRequestDTO
     * @param httpServletRequest
     * @return
     */
    MetaDTO getMetaDTO(MetaRequestDTO metaRequestDTO, HttpServletRequest httpServletRequest);
}
