<script lang="ts" setup>
import { RouteNames } from '#/types/routes'
</script>

<template>
  <NuxtLink
    class="submit-application-btn"
    :to="{ name: RouteNames.Feedback }"
  >
    Оставить заявку
  </NuxtLink>
</template>

<style lang='scss' scoped>
.submit-application-btn {
  padding: vw(14) vw(45);
  background: #0e88f7;
  border-radius: vw(30);

  font-weight: 600;
  font-size: vw(20);
  color: #fff;

  box-shadow: 0 0 vw(30) 0 rgba(#0e88f7, 0.25);

  @include xxl {
    padding: xxl-vw(14) xxl-vw(45);
    border-radius: xxl-vw(30);

    font-size: xxl-vw(20);

    box-shadow: 0 0 xxl-vw(30) 0 rgba(#0e88f7, 0.25);
  }

  @include lg {
    padding: lg-vw(14) lg-vw(30);
    border-radius: lg-vw(30);

    font-size: lg-vw(20);

    box-shadow: 0 0 lg-vw(30) 0 rgba(#0e88f7, 0.25);
  }

  @include sm {
    padding: sm-vw(14) sm-vw(40);
    border-radius: sm-vw(30);

    font-size: sm-vw(16);

    box-shadow: 0 0 sm-vw(30) 0 rgba(#0e88f7, 0.25);
  }
}
</style>
