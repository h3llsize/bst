<script lang='ts' setup>
import BurgerModal from '#/components/shared/burger/burger-modal.vue'
import Burger from '#/components/shared/burger/burger.vue'
import Cart from '#/components/shared/cart.vue'
import Logo from '#/components/shared/logo.vue'
import Nav from '#/components/shared/nav.vue'
import PhoneNotice from '#/components/shared/phone-notice.vue'
import SelectCity from '#/components/shared/city/select-city.vue'
import SubmitApplication from '#/components/shared/submit-application.vue'

const burgerActive = ref<boolean>(false)
</script>

<template>
  <header class="header">
    <div class="container">
      <Logo />
      <SelectCity class="header__city" />

      <Nav
        class="header__nav"
        direction="row"
      />
      <SubmitApplication class="header__btn" />
      <Cart class="header__cart" />

      <PhoneNotice class="header__phone-notice" />
      <Burger
        class="header__burger"
        @click="burgerActive = true"
      />
    </div>

    <PhoneNotice class="phone-notice" />
    <BurgerModal v-model="burgerActive" />
  </header>
</template>

<style lang='scss' scoped>
.header {
  position: relative;
  z-index: 10;

  min-height: vw(75);

  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000000, 0.05);

  @include xxl {
    min-height: xxl-vw(75);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000000, 0.05);
  }

  @include lg {
    min-height: lg-vw(75);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000000, 0.05);
  }

  @include sm {
    min-height: sm-vw(75);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#000000, 0.05);
  }
}

.container {
  display: flex;
  align-items: center;

  height: vw(75);

  @include xxl {
    height: xxl-vw(75);
  }

  @include lg {
    height: lg-vw(75);
  }

  @include sm {
    height: sm-vw(75);
  }
}

.header__city {
  margin-left: vw(20);

  @include lg {
    display: none;
  }
}

.header__nav {
  margin-left: auto;

  @include sm {
    display: none;
  }
}

.header__btn {
  margin-left: vw(50);

  @include xxl {
    margin-left: xxl-vw(50);
  }

  @include lg {
    margin-left: lg-vw(50);
  }

  @include sm {
    display: none;
  }
}

.header__cart {
  margin-left: vw(30);

  @include xxl {
    margin-left: xxl-vw(30);
  }

  @include lg {
    margin-left: lg-vw(30);
  }

  @include sm {
    display: none;
  }
}

.phone-notice {
  @include xxl {
    display: none;
  }
}

.header__phone-notice {
  display: none;

  @include sm {
    position: relative;
    right: 0;
    top: 0;
    transform: none;

    display: block;
    margin-left: auto;
    margin-right: sm-vw(30);
  }
}

.header__burger {
  display: none;

  @include sm {
    display: flex;
  }
}
</style>
