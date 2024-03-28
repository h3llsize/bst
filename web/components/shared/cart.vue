<script lang="ts" setup>
import CartIcon from '#/assets/svg/cart.svg?url'
import { RouteNames } from '#/types/routes'
</script>

<template>
  <NuxtLink
    :to="{ name: RouteNames.Cart }"
    class="cart"
  >
    <img
      :src="CartIcon"
      alt=""
    >
  </NuxtLink>
</template>

<style lang='scss' scoped>
.cart img {
  width: vw(50);
  height: vw(50);

  @include xxl {
    width: xxl-vw(50);
    height: xxl-vw(50);
  }

  @include lg {
    width: lg-vw(50);
    height: lg-vw(50);
  }

  @include sm {
    width: sm-vw(50);
    height: sm-vw(50);
  }
}
</style>
