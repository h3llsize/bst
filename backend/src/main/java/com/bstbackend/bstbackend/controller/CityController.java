package com.bstbackend.bstbackend.controller;

import com.bstbackend.bstbackend.dtos.CityDTO;
import com.bstbackend.bstbackend.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public List<CityDTO> all() {
        return cityService.all();
    }

}
