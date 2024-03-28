package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.dtos.MetaDTO;
import com.bstbackend.bstbackend.dtos.MetaRequestDTO;
import com.bstbackend.bstbackend.entity.CatalogCategory;
import com.bstbackend.bstbackend.entity.CatalogProduct;
import com.bstbackend.bstbackend.entity.CatalogSubCategory;
import com.bstbackend.bstbackend.entity.MainCatalogCategory;
import com.bstbackend.bstbackend.repo.CatalogCategoryRepository;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import com.bstbackend.bstbackend.repo.MainCatalogCategoryRepository;
import org.springframework.stereotype.Component;


@Component
public class MetaService {

    private final String phoneNumber = "+79954800666";
    private final CatalogCategoryRepository catalogCategoryRepository;

    private final CatalogSubcategoryRepository catalogSubcategoryRepository;

    private final CatalogProductsRepository catalogProductsRepository;

    private final MainCatalogCategoryRepository mainCatalogCategoryRepository;

    private final CityService cityService;

    public MetaService(CatalogCategoryRepository catalogCategoryRepository, CatalogSubcategoryRepository catalogSubcategoryRepository, CatalogProductsRepository catalogProductsRepository, MainCatalogCategoryRepository mainCatalogCategoryRepository, CityService cityService) {
        this.catalogCategoryRepository = catalogCategoryRepository;
        this.catalogSubcategoryRepository = catalogSubcategoryRepository;
        this.catalogProductsRepository = catalogProductsRepository;
        this.mainCatalogCategoryRepository = mainCatalogCategoryRepository;
        this.cityService = cityService;
    }


    public MetaDTO buildMetaDto(MetaRequestDTO metaRequestDTO) {
        if(metaRequestDTO.getType().equals("category"))
            return buildMetaDTOCategoryPage(metaRequestDTO);
        if (metaRequestDTO.getType().equals("subcategory"))
            return buildMetaDTOSubcategoryPage(metaRequestDTO);
        if(metaRequestDTO.getType().equals("item"))
            return buildMetaDTOItemPage(metaRequestDTO);
        if(metaRequestDTO.getType().equals("maincategory"))
            return buildMetaDTOMainCategoryPage(metaRequestDTO);

        return buildMetaDTOMainPage(metaRequestDTO);
    }

    public MetaDTO buildMetaDTOMainPage(MetaRequestDTO metaRequestDTO) {
        String cityName = cityService.getDeclinsionCityName(metaRequestDTO.getCity());
        String title = "Купить металл в " + cityName;
        String keywords = "купить металл в " + cityName + ", цена металл в " + cityName + "металлопрокат в " + cityName;
        String desc = "Предлагаем купить металлопрокат в " + cityName + " по низким ценам — БСТ. В наличии листы, трубы, круги, цветные металлы, черные металлы, нержавейка, поковки, проволока и т.д. Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ и СНГ. Звоните " + phoneNumber;

        return buildMetaDTO(title, keywords, desc, metaRequestDTO);
    }

    public MetaDTO buildMetaDTOCategoryPage(MetaRequestDTO metaRequestDTO) {
        CatalogCategory catalogCategory = catalogCategoryRepository.findByFlag(metaRequestDTO.getSlug());
        String cityName = cityService.getDeclinsionCityName(metaRequestDTO.getCity());
        String title = catalogCategory.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogCategory.getName() + ", " + catalogCategory.getName() + " купить, " + catalogCategory.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogCategory.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии " + buildHaving(catalogCategory) + ". Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + phoneNumber;

        return buildMetaDTO(title, keywords, desc, metaRequestDTO);
    }

    public MetaDTO buildMetaDTOSubcategoryPage(MetaRequestDTO metaRequestDTO) {
        CatalogSubCategory catalogSubcategory = catalogSubcategoryRepository.findByFlag(metaRequestDTO.getSlug());
        String cityName = cityService.getDeclinsionCityName(metaRequestDTO.getCity());
        String title = catalogSubcategory.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogSubcategory.getName() + ", " + catalogSubcategory.getName() + " купить, " + catalogSubcategory.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogSubcategory.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии . Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + phoneNumber;

        return buildMetaDTO(title, keywords, desc, metaRequestDTO);
    }

    public MetaDTO buildMetaDTOItemPage(MetaRequestDTO metaRequestDTO) {
        CatalogProduct catalogProduct = catalogProductsRepository.findBySlug(metaRequestDTO.getSlug()).get();
        String cityName = cityService.getDeclinsionCityName(metaRequestDTO.getCity());
        String title = catalogProduct.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogProduct.getName() + ", " + catalogProduct.getName() + " купить, " + catalogProduct.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogProduct.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии . Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + phoneNumber;

        return buildMetaDTO(title, keywords, desc, metaRequestDTO);
    }

    public MetaDTO buildMetaDTOMainCategoryPage(MetaRequestDTO metaRequestDTO) {
        MainCatalogCategory catalogProduct = mainCatalogCategoryRepository.findBySlug(metaRequestDTO.getSlug());
        String cityName = cityService.getDeclinsionCityName(metaRequestDTO.getCity());
        String title = catalogProduct.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogProduct.getName() + ", " + catalogProduct.getName() + " купить, " + catalogProduct.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogProduct.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии . Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + phoneNumber;

        return buildMetaDTO(title, keywords, desc, metaRequestDTO);
    }

    private MetaDTO buildMetaDTO(String title, String keywords, String desc, MetaRequestDTO metaRequestDTO)
    {
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.getOtherDTO().setTitle(title);
        metaDTO.getOtherDTO().setKeywords(keywords);
        metaDTO.getOtherDTO().setDescription(desc);

        metaDTO.getOpenGraph().setDescription(desc);
        metaDTO.getOpenGraph().setTitle(title);
        metaDTO.getOpenGraph().setType("website");
        metaDTO.getOpenGraph().setLocale("ru_RU");
        metaDTO.getOpenGraph().setLocality(cityService.getDefaultName(metaRequestDTO.getCity()));
        metaDTO.getOpenGraph().setSiteName("БСТ");

        return metaDTO;
    }
    private String buildHaving(CatalogCategory catalogCategory) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            try {
                stringBuilder.append(catalogCategory.getCatalogSubCategories().get(i).getName() + " ");
            } catch (Exception e) {
                break;
            }
        }

        return stringBuilder.toString();
    }
}
