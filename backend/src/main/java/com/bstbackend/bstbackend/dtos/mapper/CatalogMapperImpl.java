package com.bstbackend.bstbackend.dtos.mapper;

import com.bstbackend.bstbackend.dtos.*;
import com.bstbackend.bstbackend.entity.*;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CatalogMapperImpl implements CatalogMapper {


    private final CatalogProductsRepository catalogProductsRepository;

    public CatalogMapperImpl(CatalogProductsRepository catalogProductsRepository) {
        this.catalogProductsRepository = catalogProductsRepository;
    }

    @Override
    public CatalogCategoryDTO toDto(CatalogCategory catalogCategory) {
        CatalogCategoryDTO catalogCategoryDTO = new CatalogCategoryDTO();
        catalogCategoryDTO.setCatalogSubCategories(catalogCategory.getCatalogSubCategories().stream()
                .map(this::toDto).toList());
        catalogCategoryDTO.setName(catalogCategory.getName());
        catalogCategoryDTO.setSlug(catalogCategory.getFlag());
        catalogCategoryDTO.setId(catalogCategory.getId());
        catalogCategoryDTO.setImagePath(catalogCategory.getImagePath());
        return catalogCategoryDTO;
    }

    @Override
    public CatalogCategoryDTO toDtoWithCityName(CatalogCategory catalogCategory, String cityName) {
        CatalogCategoryDTO catalogCategoryDTO = new CatalogCategoryDTO();
        catalogCategoryDTO.setCatalogSubCategories(catalogCategory.getCatalogSubCategories().stream()
                .map(this::toDto).toList());
        catalogCategoryDTO.setName(catalogCategory.getName() + " в " + cityName);
        catalogCategoryDTO.setSlug(catalogCategory.getFlag());
        catalogCategoryDTO.setId(catalogCategory.getId());
        catalogCategoryDTO.setImagePath(catalogCategory.getImagePath());

        return catalogCategoryDTO;
    }

    @Override
    public CatalogSubCategoryDTO toDto(CatalogSubCategory catalogSubCategory) {
        CatalogSubCategoryDTO catalogSubCategoryDTO = new CatalogSubCategoryDTO();
        catalogSubCategoryDTO.setId(catalogSubCategory.getId());
        catalogSubCategoryDTO.setSlug(catalogSubCategory.getFlag());
        catalogSubCategoryDTO.setName(catalogSubCategory.getName());
        catalogSubCategoryDTO.setImagePath(catalogSubCategory.getImagePath());

        return catalogSubCategoryDTO;
    }
    @Override
    public CatalogSubCategoryDTO toDtoWithCityName(CatalogSubCategory catalogSubCategory, String cityName) {
        CatalogSubCategoryDTO catalogSubCategoryDTO = new CatalogSubCategoryDTO();
        catalogSubCategoryDTO.setId(catalogSubCategory.getId());
        catalogSubCategoryDTO.setSlug(catalogSubCategory.getFlag());
        catalogSubCategoryDTO.setName(catalogSubCategory.getName() + " в " + cityName);
        catalogSubCategoryDTO.setImagePath(catalogSubCategory.getImagePath());

        return catalogSubCategoryDTO;
    }


    @Override
    public CatalogSubcategoryItems toDtoWithItems(CatalogSubCategory catalogSubCategory, String cityName) {
        CatalogSubcategoryItems catalogSubcategoryItems = new CatalogSubcategoryItems();
        catalogSubcategoryItems.setSlug(catalogSubCategory.getFlag());
        catalogSubcategoryItems.setName(catalogSubCategory.getName() + " в " + cityName);
        catalogSubcategoryItems.setId(catalogSubCategory.getId());
        catalogSubcategoryItems.setProducts(catalogSubCategory.getProducts().stream().map(this::toDto).toList());

        return catalogSubcategoryItems;
    }

    @Override
    public List<CatalogSubcategoryItems> toDtoWithItems(List<CatalogProduct> catalogProducts) {
        HashMap<Long, CatalogSubcategoryItems> catalogSubcategoryItemsHashMap = new HashMap<>();

        for (CatalogProduct catalogProduct : catalogProducts) {
            Long catalogId = catalogProduct.getCatalogCategory().getId();

            if(catalogSubcategoryItemsHashMap.get(catalogId) == null) {
                CatalogSubcategoryItems catalogSubcategoryItems = new CatalogSubcategoryItems();
                catalogSubcategoryItems.setProducts(new ArrayList<>());
                catalogSubcategoryItemsHashMap.put(catalogId, catalogSubcategoryItems);
            }

            catalogSubcategoryItemsHashMap.get(catalogId).getProducts().add(toDto(catalogProduct));
        }

        return catalogSubcategoryItemsHashMap.values().stream().toList();
    }

    @Override
    public CatalogProductDTO toDto(CatalogProduct catalogProduct) {
        CatalogProductDTO catalogProductDTO = new CatalogProductDTO();
        catalogProductDTO.setId(catalogProduct.getId());
        catalogProductDTO.setName(catalogProduct.getName());
        catalogProductDTO.setPrice(catalogProduct.getPrice());
        catalogProductDTO.setSlug(catalogProduct.getSlug());
        catalogProductDTO.setPathToImage(catalogProduct.getCatalogCategory().getImagePath());

        return catalogProductDTO;
    }

    @Override
    public CatalogProductWithOptionsDTO withOptionsDTO(CatalogProduct catalogProduct, String cityName) {
        CatalogProductWithOptionsDTO catalogProductDTO = new CatalogProductWithOptionsDTO();
        catalogProductDTO.setId(catalogProduct.getId());
        catalogProductDTO.setName(catalogProduct.getShortName() + " в " + cityName);
        catalogProductDTO.setOptions(catalogProduct.getCategoryOptions().stream().map(this::paramDTO).toList());
        catalogProductDTO.setPrice(catalogProduct.getPrice());
        catalogProductDTO.setPathToImage(catalogProduct.getCatalogCategory().getImagePath());
        catalogProductDTO.setAdvantages(catalogProduct.getCatalogCategory().getAdvantages());
        catalogProductDTO.setManufacturing(catalogProduct.getCatalogCategory().getManufacturing());
        catalogProductDTO.setRange(catalogProduct.getCatalogCategory().getRange());
        catalogProductDTO.setDescription(catalogProduct.getCatalogCategory().getDescription());
        catalogProductDTO.setCategoryId(catalogProduct.getCatalogCategory().getId());

        return catalogProductDTO;
    }

    @Override
    public FilterCategoryOptionParamDTO paramDTO(FilterCategoryOption filterCategoryOption) {
        FilterCategoryOptionParamDTO filterCategoryOptionParamDTO = new FilterCategoryOptionParamDTO();
        filterCategoryOptionParamDTO.setName(filterCategoryOption.getFilterCategory().getName());
        filterCategoryOptionParamDTO.setParam(filterCategoryOption.getName());

        return filterCategoryOptionParamDTO;
    }

    @Override
    public FilterCategoryDTO toDto(FilterCategory filterCategory) {
        FilterCategoryDTO filterCategoryDTO = new FilterCategoryDTO();
        filterCategoryDTO.setId(filterCategory.getId());
        filterCategoryDTO.setName(filterCategory.getName());
        filterCategoryDTO.setCategoryOptions(filterCategory.getOptions().stream().map(this::toDto).toList());

        return filterCategoryDTO;
    }

    @Override
    public FilterCategoryOptionDTO toDto(FilterCategoryOption filterCategoryOption) {
        FilterCategoryOptionDTO filterCategoryOptionDTO = new FilterCategoryOptionDTO();
        filterCategoryOptionDTO.setId(filterCategoryOption.getId());
        filterCategoryOptionDTO.setName(filterCategoryOption.getName());

        return filterCategoryOptionDTO;
    }

    public UserRequestItem toEntity(UserRequestItemDTO userRequestItemDTO, UserRequest userRequest) {
        UserRequestItem userRequestItem = new UserRequestItem();
        userRequestItem.setAmount(userRequestItemDTO.getAmount());
        userRequestItem.setCatalogProduct(catalogProductsRepository.findById(userRequestItemDTO.getId()).get());
        userRequestItem.setUserRequest(userRequest);

        return userRequestItem;
    }

    @Override
    public UserRequest toEntity(UserRequestDTO userRequestDTO) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(userRequestDTO.getName());
        userRequest.setDescription(userRequestDTO.getComment());
        userRequest.setPhoneNumber(userRequestDTO.getTel());
        userRequest.setEmail(userRequestDTO.getEmail());
        userRequest.setItems(userRequestDTO.getCart().stream().map(r -> toEntity(r, userRequest)).toList());

        return userRequest;
    }

    @Override
    public MainCatalogCategoryDTO toDTO(MainCatalogCategory mainCatalogCategory) {
        MainCatalogCategoryDTO mainCatalogCategoryDTO = new MainCatalogCategoryDTO();
        mainCatalogCategoryDTO.setName(mainCatalogCategory.getName());
        mainCatalogCategoryDTO.setSlug(mainCatalogCategory.getSlug());
        mainCatalogCategoryDTO.setId(mainCatalogCategory.getId());

        return mainCatalogCategoryDTO;
    }


    @Override
    public CityDTO toDto(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(cityDTO.getCityName());
        cityDTO.setId(city.getId());
        return cityDTO;
    }

    @Override
    public MainCatalogCategoryDTO toDTOWithCityName(MainCatalogCategory mainCatalogCategory, String cityName) {
        MainCatalogCategoryDTO mainCatalogCategoryDTO = new MainCatalogCategoryDTO();
        mainCatalogCategoryDTO.setName(mainCatalogCategory.getName() + " в " + cityName);
        mainCatalogCategoryDTO.setSlug(mainCatalogCategory.getSlug());
        mainCatalogCategoryDTO.setId(mainCatalogCategory.getId());

        return mainCatalogCategoryDTO;
    }
}
