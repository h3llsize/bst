<script lang='ts' setup>
import ProductCard from '#/components/shared/product/product-card.vue'
import { RoutePaths } from '#/types/routes'
import type { SubCategory } from "#/types/models";

interface Props {
  category: SubCategory
}

defineProps<Props>()
</script>

<template>
  <div
    v-if="category?.products && category.products?.length"
    class="category"
  >
    <div style="display: flex; justify-content: space-between; align-items: center">
      <NuxtLink
        class="category__heading"
        :to="`${RoutePaths.SubCategory}/${category.slug}`"
      >
        {{ category.name }}
      </NuxtLink>
      <NuxtLink
        :to="`${RoutePaths.SubCategory}/${category.slug}`"
        class="more-link"
      >
        Показать все
      </NuxtLink>
    </div>

    <ul class="category__list">
      <ProductCard
        v-for="product in category.products"
        :id="product.id"
        :key="product.id"
        :name="product.name"
        :price="product.price"
        :slug="product.slug"
        :full="false"
      />
    </ul>
  </div>
</template>

<style lang='scss' scoped>
.more-link{
  font-weight: 550;
  color: #0e88f7;
  cursor: pointer;
  font-size: 18px;
}
.more-link:hover {
  text-decoration: underline;
}
.skeleton-card {
  background-color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 80px;
  border-radius: 2.14vw;
  padding: 16px 28px;
  margin-top: 16px;

  @include sm {
    flex-direction: column;
    align-items: end;
    height: auto;
  }
}

.skeleton {
  @include sm {
    width: 100% !important;
    justify-content: end;
    margin-top: 8px;
  }
}
.category {
  display: flex;
  flex-direction: column;
}

.category__heading {
  display: block;

  margin-left: vw(30);
  margin-bottom: vw(20);

  font-weight: 500;
  font-size: vw(24);
  text-decoration: underline;

  @include xxl {
    margin-left: xxl-vw(30);
    margin-bottom: xxl-vw(20);

    font-size: xxl-vw(24);
  }

  @include lg {
    margin-left: lg-vw(30);
    margin-bottom: lg-vw(20);

    font-size: lg-vw(24);
  }

  @include md {
    margin-left: md-vw(30);
    margin-bottom: md-vw(20);

    font-size: md-vw(24);
  }

  @include sm {
    margin-left: sm-vw(30);
    margin-bottom: sm-vw(20);

    font-size: sm-vw(24);
  }
}

.category__list {
  display: flex;
  flex-direction: column;
  gap: vw(20);

  @include xxl {
    gap: xxl-vw(20);
  }

  @include lg {
    gap: lg-vw(20);
  }

  @include md {
    gap: md-vw(20);
  }

  @include sm {
    gap: sm-vw(20);
  }
}

.category__show-more {
  align-self: center;

  margin-top: vw(15);

  @include xxl {
    margin-top: xxl-vw(15);
  }

  @include lg {
    margin-top: lg-vw(15);
  }

  @include md {
    margin-top: md-vw(15);
  }

  @include sm {
    margin-top: sm-vw(15);
  }
}
</style>
