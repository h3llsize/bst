package com.bstbackend.bstbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CatalogProductDTO {

    private Long id;

    private String name;

    private String pathToImage;

    private String slug;

    private Long price;
}
