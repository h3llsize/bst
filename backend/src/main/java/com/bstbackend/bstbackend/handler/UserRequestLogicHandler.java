package com.bstbackend.bstbackend.handler;

import com.bstbackend.bstbackend.dtos.UserRequestDTO;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@MappedSuperclass
public interface UserRequestLogicHandler {
    ResponseEntity<String> addRequest(@RequestBody UserRequestDTO userRequestDTO, HttpServletRequest httpServletRequest);
}
