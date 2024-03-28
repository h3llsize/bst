package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.dtos.CityDTO;
import com.bstbackend.bstbackend.dtos.mapper.CatalogMapper;
import com.bstbackend.bstbackend.repo.CityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CityService {
    private final CityRepository cityRepository;

    private final CatalogMapper catalogMapper;
    public CityService(CityRepository cityRepository, CatalogMapper catalogMapper) {
        this.cityRepository = cityRepository;
        this.catalogMapper = catalogMapper;
    }

    public List<CityDTO> all() {
        return cityRepository.findAll().stream().map(catalogMapper::toDto).toList();
    }

    public String getDefaultName(String cityName) {
        if(cityName == null)
            return "Москва";

        return Objects.requireNonNull(cityRepository.findById(cityName).orElse(null)).getCityName();
    }

    public String getDeclinsionCityName(String cityName) {
        if(cityName == null)
            return "Москве";

        return Objects.requireNonNull(cityRepository.findById(cityName).orElse(null)).getDeclinsionName();
    }
}
