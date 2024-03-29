<script setup lang='ts'>
import { RouteNames } from '#/types/routes'

const slider = [{
  heading: 'Выгодные предложения',
  desc: 'Мы поможем купить и доставить напрямую у проверенных производителей из Европы и Китая',
}, {
  heading: 'Индивидуальный подход',
  desc: 'Наши специалисты индивидуально прорабатывают каждую Вашу потребность',
}, {
  heading: 'Выгодные предложения',
  desc: ' Поставляем качественный металлопрокат от завода',
}, {
  heading: 'Собственное производство ',
  desc: 'Изготавливаем заказные позиции на собственном производстве',
}, {
  heading: 'Удобная логистика',
  desc: 'Доставляем грузы собственной логистикой до Вашего адреса',
}]

const blocks = [{
  heading: 'Сервис',
  desc: 'Мы предлагаем обработку и доставку металлопроката, включая резку, гибку, сварку, покраску и транспортировку собственным автотранспортом. Мы предоставляем металлопрокат высокого качества, индивидуальный подход и высокий уровень обслуживания. Наши специалисты всегда готовы предоставить консультации и помощь в выборе продукции. Мы также предлагаем систему скидок для постоянных клиентов. Сотрудничество с нами обеспечивает надежность и своевременность поставки металлопроката до вашего адреса.',
}, {
  heading: 'Поставляемая продукция',
  desc: 'Мы предлагаем металлопрокат и металлические изделия, соответствующие самым популярным стандартам качества, таким как ГОСТ, ОСТ, DIN, EN и др. Вся продукция сертифицирована и имеет гарантию производителя. Мы сотрудничаем с ведущими заводами-производителями металлопроката и металлических изделий, такими как “Северсталь”, “НЛМК”, “ММК” и другие. , что позволяет предлагать клиентам продукцию высокого качества по конкурентоспособным ценам.',
}, {
  heading: 'Логистика',
  desc: 'Компания обладает развитой логистической инфраструктурой, которая включает собственный автотранспорт для доставки металлопроката до объектов заказчиков. Наш автопарк включает в себя грузовики, тягачи, полуприцепы и другую технику, необходимую для перевозки металлопроката. Это позволяет компании гарантировать своевременность доставки продукции и обеспечивать ее сохранность на всех этапах транспортировки.',
}, {
  heading: 'Производство',
  desc: 'Одним из наших главных преимуществ является то, что мы можем изготавливать продукцию на заказ на нашем собственном производстве. Это означает, что мы можем изготовить позицию, которая точно соответствует Вашим требованиям и пожеланиям. Кроме того, мы гарантируем высокое качество нашей продукции и короткие сроки изготовления металлопроката.',
}]

const active = ref<number>(0)

onMounted(() => {
  setInterval(() => {
    active.value = (active.value + 1) % slider.length
  }, 5000)
})

void (await useLoadMeta({ type: RouteNames.About })).applySeoMeta()
</script>

<template>
  <section class="about">
    <div class="container">
      <div class="about__first">
        <div class="about__card">
          <h3 class="about__heading">
            БСТ — быстрые решения
          </h3>
          <p class="about__desc">
            Работаем по ЭДО. Бесплатно предоставляем официальную информацию по бухгалтерской отчетности и госзакупкам, потому что не стесняемся того, как управляем бизнесом.
          </p>
          <NuxtLink
            class="about__btn"
            :to="{ name: RouteNames.Feedback }"
          >
            Оставить заявку
          </NuxtLink>
        </div>

        <div class="about__img">
          <div class="about__slider">
            <h4 class="slider__heading">
              {{ slider[active].heading }}
            </h4>
            <p class="slider__desc">
              {{ slider[active].desc }}
            </p>
            <div class="slider__pagination">
              <span
                v-for="pag in slider"
                :key="pag.heading"
                class="slider__pag"
                :class="{ active: active === slider.indexOf(pag) }"
              />
            </div>
          </div>
        </div>
      </div>

      <ul class="about__blocks">
        <li
          v-for="block in blocks"
          :key="block.heading"
          class="about__block"
        >
          <h3 class="block__heading">
            {{ block.heading }}
          </h3>
          <p class="block__desc">
            {{ block.desc }}
          </p>
        </li>
      </ul>
    </div>
  </section>
</template>

<style lang='scss' scoped>
.about__first {
  display: grid;
  grid-template-columns: auto vw(390);
  grid-gap: vw(50);

  @include xxl {
    grid-template-columns: auto xxl-vw(390);
    grid-gap: xxl-vw(50);
  }

  @include lg {
    grid-template-columns: auto lg-vw(300);
    grid-gap: lg-vw(50);
  }

  @include md {
    grid-template-columns: 1fr;
  }
}

.about__card {
  position: relative;

  padding: vw(40) vw(90) vw(70) vw(90);
  background: #fff;
  border-radius: vw(35);
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.05);

  @include xxl {
    padding: xxl-vw(40) xxl-vw(90) xxl-vw(70) xxl-vw(90);
    border-radius: xxl-vw(35);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.05);
  }

  @include lg {
    padding: lg-vw(40) lg-vw(90) lg-vw(70) lg-vw(90);
    border-radius: lg-vw(35);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.05);
  }

  @include sm {
    padding: sm-vw(40) sm-vw(50) sm-vw(70) sm-vw(50);
    border-radius: sm-vw(35);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#000, 0.05);
  }
}

.about__heading {
  font-weight: 700;
  font-size: vw(46);
  line-height: 120%;

  margin-bottom: vw(15);

  @include xxl {
    font-size: xxl-vw(46);

    margin-bottom: xxl-vw(15);
  }

  @include lg {
    font-size: lg-vw(40);

    margin-bottom: lg-vw(15);
  }

  @include sm {
    font-size: sm-vw(40);

    margin-bottom: sm-vw(15);
  }
}

.about__desc {
  font-size: vw(28);
  line-height: 150%;

  @include xxl {
    font-size: xxl-vw(28);
  }

  @include lg {
    font-size: lg-vw(22);
  }

  @include sm {
    font-size: sm-vw(22);
  }
}

.about__btn {
  position: absolute;
  left: 50%;
  bottom: vw(-37);
  transform: translateX(-50%);

  display: flex;
  align-items: center;
  justify-content: center;

  width: vw(570);
  padding: vw(20);
  border-radius: vw(30);
  background: #0e88f7;
  box-shadow: 0 0 vw(20) 0 rgba(#0e88f7, 0.25);

  font-weight: 600;
  font-size: vw(28);
  color: white;

  @include xxl {
    bottom: xxl-vw(-37);

    width: xxl-vw(570);
    padding: xxl-vw(20);
    border-radius: xxl-vw(30);
    box-shadow: 0 0 xxl-vw(20) 0 rgba(#0e88f7, 0.25);

    font-size: xxl-vw(28);
  }

  @include lg {
    bottom: lg-vw(-37);

    width: lg-vw(400);
    padding: lg-vw(20);
    border-radius: lg-vw(30);
    box-shadow: 0 0 lg-vw(20) 0 rgba(#0e88f7, 0.25);

    font-size: lg-vw(28);
  }

  @include sm {
    bottom: sm-vw(-37);

    width: sm-vw(400);
    padding: sm-vw(20);
    border-radius: sm-vw(30);
    box-shadow: 0 0 sm-vw(20) 0 rgba(#0e88f7, 0.25);

    font-size: sm-vw(28);
  }
}

.about__img {
  position: relative;

  background: url('../assets/image/about_slider1.jpg') no-repeat;
  background-size: cover;
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.05);
  border-radius: vw(35);

  @include xxl {
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.05);
    border-radius: xxl-vw(35);
  }

  @include lg {
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.05);
    border-radius: lg-vw(35);
  }

  @include md {
    display: none;
  }
}

.about__slider {
  position: absolute;
  left: 50%;
  bottom: vw(-55);
  transform: translateX(-50%);

  width: vw(350);
  min-height: vw(150);
  padding: vw(15) vw(25) vw(30) vw(25);

  border-radius: vw(25);
  background: rgba(#fff, 0.7);
  backdrop-filter: blur(20px);

  @include xxl {
    bottom: xxl-vw(-55);

    width: xxl-vw(350);
    min-height: xxl-vw(150);
    padding: xxl-vw(15) xxl-vw(25) xxl-vw(30) xxl-vw(25);

    border-radius: xxl-vw(25);
  }

  @include lg {
    bottom: lg-vw(-55);

    width: lg-vw(250);
    min-height: lg-vw(200);
    padding: lg-vw(15) lg-vw(25) lg-vw(30) lg-vw(25);

    border-radius: lg-vw(25);
  }
}

.slider__heading {
  font-weight: 600;
  font-size: vw(20);
  line-height: 120%;

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }
}

.slider__desc {
  font-size: vw(16);

  @include xxl {
    font-size: xxl-vw(16);
  }

  @include lg {
    font-size: lg-vw(16);
  }
}

.slider__pagination {
  position: absolute;
  left: vw(25);
  right: vw(25);
  bottom: vw(15);

  display: flex;
  align-items: center;
  gap: vw(5);

  height: vw(3);

  @include xxl {
    left: xxl-vw(25);
    right: xxl-vw(25);
    bottom: xxl-vw(15);

    gap: xxl-vw(5);

    height: xxl-vw(3);
  }

  @include lg {
    left: lg-vw(25);
    right: lg-vw(25);
    bottom: lg-vw(15);

    gap: lg-vw(5);

    height: lg-vw(3);
  }

  .slider__pag {
    width: 100%;
    height: 100%;
    background: #546676;
    opacity: 0.5;

    transition:
      opacity 0.25s ease,
      transform 0.25s ease;

    &.active {
      opacity: 1;
      transform: scaleY(1.3);
    }
  }
}

.about__blocks {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: vw(80);

  margin-top: vw(120);

  @include xxl {
    grid-gap: xxl-vw(80);

    margin-top: xxl-vw(120);
  }

  @include lg {
    grid-gap: lg-vw(80);

    margin-top: lg-vw(120);
  }

  @include sm {
    grid-template-columns: 1fr;
    grid-gap: sm-vw(80);

    margin-top: sm-vw(120);
  }
}

.block__heading {
  font-weight: 600;
  font-size: vw(36);
  margin-bottom: vw(10);

  @include xxl {
    font-size: xxl-vw(36);
    margin-bottom: xxl-vw(10);
  }

  @include lg {
    font-size: lg-vw(36);
    margin-bottom: lg-vw(10);
  }

  @include sm {
    font-size: sm-vw(36);
    margin-bottom: sm-vw(10);
  }
}

.block__desc {
  font-size: vw(20);
  line-height: 150%;

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}
</style>
