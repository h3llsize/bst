package com.bstbackend.bstbackend.meta.builders;

import com.bstbackend.bstbackend.entity.MainCatalogCategory;
import com.bstbackend.bstbackend.meta.MetaConst;
import com.bstbackend.bstbackend.meta.MetaModel;
import com.bstbackend.bstbackend.meta.MetaTypes;
import com.bstbackend.bstbackend.repo.MainCatalogCategoryRepository;
import com.bstbackend.bstbackend.service.CityService;
import org.springframework.stereotype.Component;

@Component
public class MainCategoryMetaBuilder extends MetaAbstractBuilder{
    private final MainCatalogCategoryRepository mainCatalogCategoryRepository;

    protected MainCategoryMetaBuilder(CityService cityService, MainCatalogCategoryRepository mainCatalogCategoryRepository) {
        super(cityService);
        this.mainCatalogCategoryRepository = mainCatalogCategoryRepository;
    }

    @Override
    public MetaTypes type() {
        return MetaTypes.MAIN_CATEGORY;
    }

    @Override
    public MetaModel buildMeta(String slug, String cityName) {
        MainCatalogCategory catalogProduct = mainCatalogCategoryRepository.findBySlug(slug);
        String title = catalogProduct.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogProduct.getName() + ", " + catalogProduct.getName() + " купить, " + catalogProduct.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogProduct.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии . Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + MetaConst.phoneNumber;

        return new MetaModel(title, desc, keywords);
    }
}
