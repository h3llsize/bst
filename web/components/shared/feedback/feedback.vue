<script lang='ts' setup>
import FeedbackForm from './feedback-form.vue'

defineProps<{ hasCart?: boolean }>()
</script>

<template>
  <div class="feedback">
    <div class="feedback__left">
      <h3 class="feedback__heading">
        <slot />
      </h3>
      <p class="feedback__desc">
        Оставьте свои контактные данные и наш менеджер свяжется с вами в течении 20 минут по нужной Вам продукции
      </p>
      <span class="feedback__condition">
        *Нажимая на кнопку ОСТАВИТЬ ЗАЯВКУ, я подтверждаю, что ознакомился с политикой обработки персональных данных и даю согласие на обработку
      </span>
    </div>

    <FeedbackForm :has-cart="hasCart" />
  </div>
</template>

<style lang='scss' scoped>
.feedback {
  display: grid;
  grid-template-columns: auto vw(700);
  grid-gap: vw(70);

  @include xxl {
    grid-template-columns: auto xxl-vw(700);
    grid-gap: xxl-vw(70);
  }

  @include lg {
    display: flex;
    flex-direction: column;
    gap: lg-vw(50);
  }

  @include sm {
    gap: sm-vw(50);
  }
}

.feedback__heading {
  font-weight: 600;
  font-size: vw(36);
  margin-bottom: vw(15);

  @include xxl {
    font-size: xxl-vw(36);
    margin-bottom: xxl-vw(15);
  }

  @include lg {
    font-size: lg-vw(36);
    margin-bottom: lg-vw(15);
  }

  @include sm {
    font-size: sm-vw(36);
    margin-bottom: sm-vw(15);
  }
}

.feedback__desc {
  font-weight: 500;
  font-size: vw(32);

  margin-bottom: vw(20);

  @include xxl {
    font-size: xxl-vw(32);

    margin-bottom: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(32);

    margin-bottom: lg-vw(20);
  }

  @include sm {
    font-size: sm-vw(32);

    margin-bottom: sm-vw(20);
  }
}

.feedback__condition {
  font-size: vw(18);

  @include xxl {
    font-size: xxl-vw(18);
  }

  @include lg {
    font-size: lg-vw(18);
  }

  @include sm {
    font-size: sm-vw(18);
  }
}
</style>
