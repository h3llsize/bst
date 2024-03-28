package com.bstbackend.bstbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_request")
@Getter
@Setter
public class UserRequest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRequest")
    private List<UserRequestItem> items;
}
