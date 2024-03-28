package com.bstbackend.bstbackend.handler.impl;

import com.bstbackend.bstbackend.dtos.UserRequestDTO;
import com.bstbackend.bstbackend.handler.UserRequestLogicHandler;
import com.bstbackend.bstbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRequestLogicHandlerImpl implements UserRequestLogicHandler {

    private final UserService userService;

    public UserRequestLogicHandlerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> addRequest(UserRequestDTO userRequestDTO, HttpServletRequest httpServletRequest) {
        userService.saveUserRequest(userRequestDTO);
        return ResponseEntity.ok("Successful added user request");
    }
}
