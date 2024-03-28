package com.bstbackend.bstbackend.meta.builders;

import com.bstbackend.bstbackend.entity.CatalogCategory;
import com.bstbackend.bstbackend.meta.MetaConst;
import com.bstbackend.bstbackend.meta.MetaModel;
import com.bstbackend.bstbackend.meta.MetaTypes;
import com.bstbackend.bstbackend.repo.CatalogCategoryRepository;
import com.bstbackend.bstbackend.service.CityService;
import org.springframework.stereotype.Component;

@Component
public class CategoryMetaBuilder extends MetaAbstractBuilder {

    private final CatalogCategoryRepository catalogCategoryRepository;

    protected CategoryMetaBuilder(CityService cityService, CatalogCategoryRepository catalogCategoryRepository) {
        super(cityService);
        this.catalogCategoryRepository = catalogCategoryRepository;
    }

    @Override
    public MetaTypes type() {
        return MetaTypes.CATEGORY;
    }

    @Override
    public MetaModel buildMeta(String slug, String cityName) {
        CatalogCategory catalogCategory = catalogCategoryRepository.findByFlag(slug);
        String title = catalogCategory.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogCategory.getName() + ", " + catalogCategory.getName() + " купить, " + catalogCategory.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogCategory.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии " + buildHaving(catalogCategory) + ". Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + MetaConst.phoneNumber;

        return new MetaModel(title, desc, keywords);
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
