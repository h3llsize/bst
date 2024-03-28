package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.dtos.MetaRequestDTO;
import com.bstbackend.bstbackend.handler.MetaLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meta")
public class MetaController {

    private final MetaLogicHandler metaLogicHandler;

    public MetaController(MetaLogicHandler metaLogicHandler) {
        this.metaLogicHandler = metaLogicHandler;
    }

    @PostMapping("/get")
    public MetaDTO getMetaDTO(@RequestBody MetaRequestDTO metaRequestDTO, HttpServletRequest httpServletRequest) {
        return metaLogicHandler.getMetaDTO(metaRequestDTO, httpServletRequest);
    }
}
