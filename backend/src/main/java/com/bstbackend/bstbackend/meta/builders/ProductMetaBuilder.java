package com.bstbackend.bstbackend.meta.builders;

import com.bstbackend.bstbackend.entity.CatalogProduct;
import com.bstbackend.bstbackend.meta.MetaConst;
import com.bstbackend.bstbackend.meta.MetaModel;
import com.bstbackend.bstbackend.meta.MetaTypes;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import com.bstbackend.bstbackend.service.CityService;
import org.springframework.stereotype.Component;

@Component
public class ProductMetaBuilder extends MetaAbstractBuilder {
    private final CatalogProductsRepository catalogProductsRepository;

    protected ProductMetaBuilder(CityService cityService, CatalogProductsRepository catalogProductsRepository) {
        super(cityService);
        this.catalogProductsRepository = catalogProductsRepository;
    }

    @Override
    public MetaTypes type() {
        return MetaTypes.PRODUCT;
    }

    @Override
    public MetaModel buildMeta(String slug, String cityName) {
        CatalogProduct catalogProduct = catalogProductsRepository.findBySlug(slug).get();
        String title = catalogProduct.getName() + " купить по низким ценам в " + cityName;
        String keywords = catalogProduct.getName() + ", " + catalogProduct.getName() + " купить, " + catalogProduct.getName() + " купить в " + cityName + ", ";
        String desc = "Предлагаем купить " + catalogProduct.getName() + " в " + cityName  + " по низким ценам — БСТ. В наличии . Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ. Звоните " + MetaConst.phoneNumber;

        return new MetaModel(title, desc, keywords);
    }
}
