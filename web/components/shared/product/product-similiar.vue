<script lang='ts' setup>
import ProductCard from './product-card.vue'
import ProductAdvantages from './product-advantages.vue'
import type { Product } from '#/types/models'

defineProps<{ products: Product[] }>()
</script>

<template>
  <div class="product-similiar">
    <h3 class="heading">
      Похожие товары
    </h3>

    <div class="content">
      <div class="subcategory__list">
        <ProductCard
          v-for="product in products"
          v-show="products.length"
          :id="product.id"
          :key="product.id"
          class="similiar__product-card"
          :name="product.name"
          :price="product.price"
          :slug="product.slug"
          :full="false"
          :has-btn="false"
        />
        <p
          v-if="products.length === 0"
          class="not-found"
        >
          Ничего не найдено
        </p>
      </div>

      <ProductAdvantages />
    </div>
  </div>
</template>

<style lang='scss' scoped>
.product-similiar {
  border-top: vw(2) solid #0e88f7;
  padding-top: vw(50);

  margin-bottom: vw(50);

  @include xxl {
    border-top: xxl-vw(2) solid #0e88f7;
    padding-top: xxl-vw(50);

    margin-bottom: xxl-vw(50);
  }

  @include lg {
    border-top: lg-vw(2) solid #0e88f7;
    padding-top: lg-vw(50);

    margin-bottom: lg-vw(50);
  }

  @include sm {
    border-top: sm-vw(2) solid #0e88f7;
    padding-top: sm-vw(50);

    margin-bottom: sm-vw(50);
  }
}

.content {
  display: grid;
  grid-template-columns: auto vw(350);
  grid-gap: vw(30);

  @include xxl {
    grid-template-columns: auto xxl-vw(350);
    grid-gap: xxl-vw(30);
  }

  @include lg {
    grid-template-columns: auto lg-vw(350);
    grid-gap: lg-vw(30);
  }

  @include sm {
    grid-template-columns: 1fr;
    grid-gap: sm-vw(30);
  }
}

.heading {
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

.subcategory__list {
  display: flex;
  flex-direction: column;
  gap: vw(20);

  @include xxl {
    gap: xxl-vw(20);
  }

  @include lg {
    gap: lg-vw(20);
  }

  @include sm {
    gap: sm-vw(20);
  }
}

.not-found {
  font-weight: 500;
  font-size: vw(32);
  text-align: center;

  @include xxl {
    font-size: xxl-vw(32);
  }

  @include lg {
    font-size: lg-vw(32);
  }

  @include sm {
    font-size: sm-vw(32);
  }
}
</style>
