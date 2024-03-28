package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.dtos.*;
import com.bstbackend.bstbackend.dtos.mapper.CatalogMapper;
import com.bstbackend.bstbackend.repo.CatalogCategoryRepository;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import com.bstbackend.bstbackend.repo.FilterCategoryRepository;
import com.bstbackend.bstbackend.repo.MainCatalogCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class CategoryService {

    private final CatalogCategoryRepository categoryRepository;

    private final CatalogSubcategoryRepository catalogSubcategoryRepository;

    private final FilterCategoryRepository filterCategoryRepository;

    private final CatalogMapper catalogMapper;
    private final MainCatalogCategoryRepository mainCatalogCategoryRepository;

    private final CityService cityService;


    public CategoryService(CatalogCategoryRepository categoryRepository, CatalogSubcategoryRepository catalogSubcategoryRepository, FilterCategoryRepository filterCategoryRepository, CatalogMapper catalogMapper, MainCatalogCategoryRepository mainCatalogCategoryRepository, CityService cityService) {
        this.categoryRepository = categoryRepository;
        this.catalogSubcategoryRepository = catalogSubcategoryRepository;
        this.filterCategoryRepository = filterCategoryRepository;
        this.catalogMapper = catalogMapper;
        this.mainCatalogCategoryRepository = mainCatalogCategoryRepository;
        this.cityService = cityService;
    }

    public List<CatalogCategoryDTO> getAllCatalogCategoryBySlug(String slug) {
        return categoryRepository.findAllByMainCatalogCategory_Slug(slug).stream().map(catalogMapper::toDto).toList();
    }

    public CatalogCategoryDTO getCatalogCategoryBySlug(String slug, String cityName) {
        return catalogMapper.toDtoWithCityName(categoryRepository.findByFlag(slug), cityService.getDeclinsionCityName(cityName));
    }

    public List<CatalogSubcategoryItems> getCatalogSubCategoriesBySlug(String slug, String cityName) {
        return catalogSubcategoryRepository.findAllByCatalogCategorySlug(slug).stream().map(z -> catalogMapper.toDtoWithItems(z, cityService.getDeclinsionCityName(cityName))).toList();
    }

    public List<CatalogSubcategoryItems> getCatalogSubCategoriesBySlugAll(String slug, String cityName) {
        return catalogSubcategoryRepository.findAllByCatalogCategory_Flag(slug).stream().map(z -> catalogMapper.toDtoWithItems(z, cityService.getDeclinsionCityName(cityName))).toList();
    }

    public MainCatalogCategoryDTO getMainCatalogCategory(String slug, String cityName) {
        return catalogMapper.toDTOWithCityName(mainCatalogCategoryRepository.findBySlug(slug), cityService.getDeclinsionCityName(cityName));
    }

    public List<FilterCategoryDTO> listFilters(Long categoryId) {
        return filterCategoryRepository.findAllByCatalogCategory_Id(categoryId).stream().map(catalogMapper::toDto).toList();
    }

    public Page<CatalogSubCategoryDTO> findSubcategories(String query, Integer page, String cityName) {
        if (query.isEmpty())
        {
            return catalogSubcategoryRepository.findAll(Pageable.ofSize(5).withPage(page))
                    .map(c -> catalogMapper.toDtoWithCityName(c, cityService.getDeclinsionCityName(cityName)));
        }

        return catalogSubcategoryRepository.findByQuery("%" + query.toLowerCase(Locale.ROOT) + "%", Pageable.ofSize(5).withPage(page))
                .map(c -> catalogMapper.toDtoWithCityName(c, cityService.getDeclinsionCityName(cityName)));
    }

    public List<MainCatalogCategoryDTO> findAllMainCategories() {
        return mainCatalogCategoryRepository.findAll().stream().map(catalogMapper::toDTO).toList();
    }

    public CatalogSubCategoryDTO findCatalogSubCategoryBySlug(String slug, String cityPattern) {
        return catalogMapper.toDtoWithCityName(catalogSubcategoryRepository.findByFlag(slug), cityService.getDeclinsionCityName(cityPattern));
    }
}
