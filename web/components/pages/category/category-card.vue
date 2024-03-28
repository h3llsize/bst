<script lang='ts' setup>
import type { SubCategory } from '#/types/models'
import type { RouteNames } from '#/types/routes'

interface Props {
  item: SubCategory
  type: RouteNames.Category | RouteNames.SubCategory
}

defineProps<Props>()
</script>

<template>
  <NuxtLink
    class="category-card"
    :to="`/${type}/${item.slug}`"
  >
    <img
      :src="`https://bst-sl.ru/${item.imagePath}`"
      :alt="item.name"
      class="category-card__img"
    >

    <p class="category-card__item">
      {{ item.name }}
    </p>
  </NuxtLink>
</template>

<style lang='scss' scoped>
.category-card {
  position: relative;

  min-height: vw(300);
  padding: vw(20);
  padding-top: vw(200);
  border-radius: vw(20);

  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.05);

  @include xxl {
    min-height: xxl-vw(300);
    padding: xxl-vw(20);
    padding-top: xxl-vw(200);
    border-radius: xxl-vw(20);

    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.05);
  }

  @include lg {
    min-height: lg-vw(300);
    padding: lg-vw(20);
    padding-top: lg-vw(200);
    border-radius: lg-vw(20);

    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.05);
  }
}

.category-card__img {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;

  width: 100%;
  height: vw(200);
  background: #fff;
  border-top-right-radius: vw(20);
  border-top-left-radius: vw(20);

  object-fit: cover;

  @include xxl {
    height: xxl-vw(200);
    border-top-right-radius: xxl-vw(20);
    border-top-left-radius: xxl-vw(20);
  }

  @include lg {
    height: lg-vw(200);
    border-top-right-radius: lg-vw(20);
    border-top-left-radius: lg-vw(20);
  }
}

.category-card__item {
  display: block;
  font-weight: 500;
  font-size: vw(24);

  margin-top: vw(20);

  @include xxl {
    font-size: xxl-vw(24);

    margin-top: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);

    margin-top: lg-vw(20);
  }
}
</style>
