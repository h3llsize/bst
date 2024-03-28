package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.UserRequestDTO;
import com.bstbackend.bstbackend.handler.UserRequestLogicHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class UserRequestController {

    private final UserRequestLogicHandler userRequestLogicHandler;

    public UserRequestController(UserRequestLogicHandler userRequestLogicHandler) {
        this.userRequestLogicHandler = userRequestLogicHandler;
    }

    @PostMapping("/request")
    public ResponseEntity<String> addRequest(@RequestBody UserRequestDTO userRequestDTO, HttpServletRequest httpServletRequest) throws EmailException {
        return userRequestLogicHandler.addRequest(userRequestDTO, httpServletRequest);
    }
}
