package com.bstbackend.bstbackend.dtos;

import lombok.Data;

@Data
public class MetaOpenGraphDTO {
    private String title;

    private String description;

    private String type;

    private String image;

    private String locale;

    private String locality;

    private String siteName;
}
