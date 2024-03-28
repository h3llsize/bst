package com.bstbackend.bstbackend.meta.builders;

import com.bstbackend.bstbackend.entity.CatalogSubCategory;
import com.bstbackend.bstbackend.meta.MetaConst;
import com.bstbackend.bstbackend.meta.MetaModel;
import com.bstbackend.bstbackend.meta.MetaTypes;
import com.bstbackend.bstbackend.repo.CatalogSubcategoryRepository;
import com.bstbackend.bstbackend.service.CityService;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMetaBuilder extends MetaAbstractBuilder {
    private final CatalogSubcategoryRepository catalogSubcategoryRepository;

    public SubcategoryMetaBuilder(CatalogSubcategoryRepository catalogSubcategoryRepository, CityService cityService) {
        super(cityService);
        this.catalogSubcategoryRepository = catalogSubcategoryRepository;
    }

    @Override
    public MetaTypes type() {
        return MetaTypes.SUBCATEGORY;
    }

    @Override
    public MetaModel buildMeta(String slug, String cityName) {
        CatalogSubCategory catalogSubcategory = catalogSubcategoryRepository.findByFlag(slug);

        String title = catalogSubcategory.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogSubcategory.getName() + ", " + catalogSubcategory.getName() + " купить, " + catalogSubcategory.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogSubcategory.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии . Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + MetaConst.phoneNumber;

        return new MetaModel(title, desc, keywords);
    }
}
