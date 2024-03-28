package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.dtos.UserRequestDTO;
import com.bstbackend.bstbackend.dtos.mapper.CatalogMapper;
import com.bstbackend.bstbackend.entity.UserRequest;
import com.bstbackend.bstbackend.repo.UserRequestRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRequestRepository userRequestRepository;

    private final CatalogMapper catalogMapper;
    private final MailService mailService;

    public UserService(UserRequestRepository userRequestRepository, CatalogMapper catalogMapper, MailService mailService) {
        this.userRequestRepository = userRequestRepository;
        this.catalogMapper = catalogMapper;
        this.mailService = mailService;
    }

    public void saveUserRequest(UserRequestDTO userRequestDTO) {
        UserRequest userRequest = catalogMapper.toEntity(userRequestDTO);

        mailService.sendUserRequest(userRequest);

        userRequestRepository.save(catalogMapper.toEntity(userRequestDTO));
    }
}
