<script lang='ts' setup>
import ProductCard from '#/components/shared/product/product-card.vue'
import Loader from '#/components/shared/loader.vue'

import Feedback from '#/components/shared/feedback/feedback.vue'

import { Services } from '#/utils/api'
import type { Product } from '#/types/models'
import { RouteNames } from '#/types/routes'

const store = useStore()

const products = ref<Product[]>([])
const showLoader = ref<boolean>(true)

try {
  const values = await Promise.all(store.getCartAction().map(product => Services.getProduct(product.id)))

  products.value = values.filter(el => !!el.id) as unknown as Product[]
  showLoader.value = false
}
catch (error) {

}

void (await useLoadMeta({ type: RouteNames.Cart })).applySeoMeta()
</script>

<template>
  <section class="cart">
    <div class="container">
      <h2 class="cart__heading">
        Корзина
      </h2>
      <p class="cart__amount">
        Всего: {{ products.length }} товар(а)
      </p>

      <ul class="cart__products">
        <template v-if="!showLoader">
          <ProductCard
            v-for="product in products"
            :id="product.id"
            :key="product.id"
            :name="product.name"
            :price="product.price"
            :slug="product.slug"
            :full="true"
          />
        </template>

        <div
          v-else
          class="loader-box"
        >
          <Loader />
        </div>
      </ul>

      <Feedback
        :has-cart="true"
        class="cart__feedback"
      >
        Заявка на заказ
      </Feedback>
    </div>
  </section>
</template>

<style lang='scss' scoped>
.cart__heading {
  font-weight: 600;
  font-size: vw(42);

  margin-bottom: vw(10);

  @include xxl {
    font-size: xxl-vw(42);

    margin-bottom: xxl-vw(10);
  }

  @include lg {
    font-size: lg-vw(42);

    margin-bottom: lg-vw(10);
  }

  @include sm {
    font-size: sm-vw(42);

    margin-bottom: sm-vw(10);
  }
}

.cart__amount {
  font-size: vw(28);

  margin-bottom: vw(40);

  @include xxl {
    font-size: xxl-vw(28);

    margin-bottom: xxl-vw(40);
  }

  @include lg {
    font-size: lg-vw(28);

    margin-bottom: lg-vw(40);
  }

  @include sm {
    font-size: sm-vw(28);

    margin-bottom: sm-vw(40);
  }
}

.loader-box {
  display: flex;
  align-items: center;
  justify-content: center;
}

.cart__products {
  display: flex;
  flex-direction: column;
  gap: vw(20);

  margin-bottom: vw(20);

  @include xxl {
    gap: xxl-vw(20);

    margin-bottom: xxl-vw(20);
  }

  @include lg {
    gap: lg-vw(20);

    margin-bottom: lg-vw(20);
  }

  @include sm {
    gap: sm-vw(20);

    margin-bottom: sm-vw(20);
  }
}

.cart__price {
  font-weight: 600;
  font-size: vw(38);

  text-align: end;

  @include xxl {
    font-size: xxl-vw(38);
  }

  @include lg {
    font-size: lg-vw(38);
  }

  @include sm {
    font-size: sm-vw(38);
  }
}

.cart__feedback {
  margin-top: vw(70);

  @include xxl {
    margin-top: xxl-vw(70);
  }

  @include lg {
    margin-top: lg-vw(70);
  }

  @include sm {
    margin-top: sm-vw(70);
  }
}
</style>
