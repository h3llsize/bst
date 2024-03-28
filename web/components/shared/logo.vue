<script lang="ts" setup>
import { RouteNames } from '#/types/routes'
</script>

<template>
  <NuxtLink
    :to="{ name: RouteNames.Catalog }"
    class="logo noselect"
  >
    <img
      src="../../assets/image/logo-mini.png"
      alt="logo"
      class="logo-mini"
    >
    <span class="logo-line" />
    <span class="logo-name">БСТ</span>
  </NuxtLink>
</template>

<style lang='scss' scoped>
.logo {
  display: flex;
  align-items: center;
}

.logo-mini {
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

.logo-line {
  display: block;
  width: vw(2);
  height: vw(45);
  background: #062120;
  border-radius: vw(10);

  margin-left: vw(10);
  margin-right: vw(10);

  @include xxl {
    width: xxl-vw(2);
    height: xxl-vw(45);
    border-radius: xxl-vw(10);

    margin-left: xxl-vw(10);
    margin-right: xxl-vw(10);
  }

  @include lg {
    width: lg-vw(2);
    height: lg-vw(45);
    border-radius: lg-vw(10);

    margin-left: lg-vw(10);
    margin-right: lg-vw(10);
  }

  @include sm {
    width: sm-vw(2);
    height: sm-vw(45);
    border-radius: sm-vw(10);

    margin-left: sm-vw(10);
    margin-right: sm-vw(10);
  }
}

.logo-name {
  font-weight: 900;
  font-size: vw(42);

  @include xxl {
    font-size: xxl-vw(42);
  }

  @include lg {
    font-size: lg-vw(42);
  }

  @include sm {
    font-size: sm-vw(42);
  }
}
</style>
