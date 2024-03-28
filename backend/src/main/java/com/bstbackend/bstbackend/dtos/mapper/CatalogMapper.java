package com.bstbackend.bstbackend.dtos.mapper;

import com.bstbackend.bstbackend.dtos.*;
import com.bstbackend.bstbackend.entity.*;
import jakarta.persistence.MappedSuperclass;

import java.util.List;

@MappedSuperclass
public interface CatalogMapper {
    CatalogCategoryDTO toDto(CatalogCategory catalogCategory);

    CatalogCategoryDTO toDtoWithCityName(CatalogCategory catalogCategory, String cityName);

    CatalogSubcategoryItems toDtoWithCityNameStart(CatalogSubCategory catalogSubCategory, String cityName, List<CatalogProduct> catalogProducts);

    CatalogSubCategoryDTO toDto(CatalogSubCategory catalogSubCategory);

    CatalogSubCategoryDTO toDtoWithCityName(CatalogSubCategory catalogSubCategory, String cityName);

    CatalogSubcategoryItems toDtoWithItems(CatalogSubCategory catalogSubCategory, String cityName);

    List<CatalogSubcategoryItems> toDtoWithItems(List<CatalogProduct> catalogProducts);

    CatalogProductDTO toDto(CatalogProduct catalogProduct);

    CatalogProductWithOptionsDTO withOptionsDTO(CatalogProduct catalogProduct, String cityName);

    FilterCategoryOptionParamDTO paramDTO(FilterCategoryOption filterCategoryOption);

    FilterCategoryDTO toDto(FilterCategory filterCategory);

    FilterCategoryOptionDTO toDto(FilterCategoryOption filterCategoryOption);

    UserRequest toEntity(UserRequestDTO userRequestDTO);

    MainCatalogCategoryDTO toDTO(MainCatalogCategory mainCatalogCategory);

    CityDTO toDto(City city);

    MainCatalogCategoryDTO toDTOWithCityName(MainCatalogCategory mainCatalogCategory, String cityName);
}
