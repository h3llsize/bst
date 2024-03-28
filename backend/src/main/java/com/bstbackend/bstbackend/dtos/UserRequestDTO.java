package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    private String name;

    private String tel;

    private String email;

    private String comment;

    private List<UserRequestItemDTO> cart;
}
