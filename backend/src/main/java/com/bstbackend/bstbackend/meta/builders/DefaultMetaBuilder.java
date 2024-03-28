package com.bstbackend.bstbackend.meta.builders;

import com.bstbackend.bstbackend.meta.MetaConst;
import com.bstbackend.bstbackend.meta.MetaModel;
import com.bstbackend.bstbackend.meta.MetaTypes;
import com.bstbackend.bstbackend.service.CityService;
import org.springframework.stereotype.Component;

@Component
public class DefaultMetaBuilder extends MetaAbstractBuilder{
    protected DefaultMetaBuilder(CityService cityService) {
        super(cityService);
    }

    @Override
    public MetaTypes type() {
        return MetaTypes.DEFAULT;
    }

    @Override
    public MetaModel buildMeta(String slug, String cityName) {
        String title = "Купить металл в " + cityName;
        String keywords = "купить металл в " + cityName + ", цена металл в " + cityName + "металлопрокат в " + cityName;
        String desc = "Предлагаем купить металлопрокат в " + cityName + " по низким ценам — БСТ. В наличии листы, трубы, круги, цветные металлы, черные металлы, нержавейка, поковки, проволока и т.д. Продажа оптом и в розницу. Размеры и цены на сайте. Доставка по РФ и СНГ. Звоните " + MetaConst.phoneNumber;

        return new MetaModel(title, desc, keywords);
    }
}
