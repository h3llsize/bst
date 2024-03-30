<script lang='ts' setup>
import { RoutePaths } from '#/types/routes'
import type { CartProduct } from '#/types/models'

interface Props {
  id: number
  slug: string
  name: string
  price: number
  full: boolean
  hasBtn?: boolean
}

const props = withDefaults(defineProps<Props>(), { hasBtn: true })
const store = useStore()

const product = computed<CartProduct>(() => store.getProduct(props.id))

const isShowPrice = computed(() => props.full && props.price > 299)

function changeAmount(amount: number) {
  store.addProduct(props.id, product.value.amount + amount)
}
</script>

<template>
  <NuxtLink
    class="product-card"
    :to="`${RoutePaths.Product}/${slug}`"
  >
    <p class="product-card__name">
      {{ name }}
    </p>

    <span
      v-if="isShowPrice"
      class="product-card__sum"
    >
      от {{ (price * product.amount).toLocaleString() }} &#8381;
    </span>

    <div
      v-if="product.amount && hasBtn"
      class="product-card__count"
      @click.prevent="''"
    >
      <button
        class="btn"
        @click.prevent="changeAmount(-1)"
      >
        <span class="arrow arrow_minus" />
      </button>
      <span class="amount">
        {{ product.amount }}
      </span>
      <button
        class="btn"
        @click.prevent="changeAmount(1)"
      >
        <span class="arrow arrow_plus" />
      </button>
    </div>

    <button
      v-if="!product.amount && hasBtn"
      class="product-card__add"
      @click.prevent="changeAmount(1)"
    >
      <span class="product-card__price">
        {{ price > 299 ? `${price.toLocaleString()} &#8381;` : 'договорная' }}
      </span>
      <span class="product-card__text">
        в корзину
      </span>
    </button>
  </NuxtLink>
</template>

<style lang='scss' scoped>
.product-card {
  display: flex;
  align-items: center;

  width: 100%;
  padding: vw(17) vw(30);
  border-radius: vw(30);
  background: #fff;
  box-shadow: 0 0 vw(30) rgba(#000, 0.05);

  @include xxl {
    padding: xxl-vw(17) xxl-vw(30);
    border-radius: xxl-vw(30);
    box-shadow: 0 0 xxl-vw(30) rgba(#000, 0.05);
  }

  @include lg {
    padding: lg-vw(17) lg-vw(30);
    border-radius: lg-vw(30);
    box-shadow: 0 0 lg-vw(30) rgba(#000, 0.05);
  }

  @include md {
    padding: md-vw(17) md-vw(30);
    border-radius: md-vw(30);
    box-shadow: 0 0 md-vw(30) rgba(#000, 0.05);
  }

  @include sm {
    flex-direction: column;
    align-items: flex-start;
    gap: sm-vw(20);

    padding: sm-vw(17) sm-vw(30);
    border-radius: sm-vw(30);
    box-shadow: 0 0 sm-vw(30) rgba(#000, 0.05);
  }
}

.product-card__name {
  max-width: vw(700);

  font-size: vw(18);

  margin-right: auto;

  @include xxl {
    max-width: xxl-vw(700);

    font-size: xxl-vw(18);
  }

  @include lg {
    max-width: 100%;

    font-size: lg-vw(18);
  }

  @include md {
    font-size: md-vw(18);
  }

  @include sm {
    font-size: sm-vw(18);
  }
}

.product-card__sum {
  font-weight: 500;
  font-size: vw(26);
  margin-right: vw(10);
  white-space: nowrap;

  @include xxl {
    font-size: xxl-vw(26);
    margin-right: xxl-vw(10);
  }

  @include lg {
    font-size: lg-vw(26);
    margin-right: lg-vw(10);
  }

  @include md {
    font-size: md-vw(26);
    margin-right: md-vw(10);
  }

  @include sm {
    display: none;
  }
}

.product-card__add {
  flex-shrink: 0;
  margin-left: vw(20);

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  width: vw(160);
  height: vw(50);
  border-radius: vw(20);
  background: #0e88f7;
  box-shadow: 0 0 vw(30) rgba(#0e88f7, 0.25);

  @include xxl {
    margin-left: xxl-vw(20);

    width: xxl-vw(160);
    height: xxl-vw(50);
    border-radius: xxl-vw(20);
    box-shadow: 0 0 xxl-vw(30) rgba(#0e88f7, 0.25);
  }

  @include lg {
    margin-left: lg-vw(20);

    width: lg-vw(160);
    height: lg-vw(50);
    border-radius: lg-vw(20);
    box-shadow: 0 0 lg-vw(30) rgba(#0e88f7, 0.25);
  }

  @include md {
    margin-left: md-vw(20);

    width: md-vw(160);
    height: md-vw(50);
    border-radius: md-vw(20);
    box-shadow: 0 0 md-vw(30) rgba(#0e88f7, 0.25);
  }

  @include sm {
    margin-left: 0;
    align-self: flex-end;

    width: sm-vw(160);
    height: sm-vw(50);
    border-radius: sm-vw(20);
    box-shadow: 0 0 sm-vw(30) rgba(#0e88f7, 0.25);
  }
}

.product-card__count {
  flex-shrink: 0;
  margin-left: vw(20);

  display: flex;
  align-items: center;
  justify-content: space-between;

  width: vw(160);
  height: vw(50);

  border-radius: vw(20);
  border: vw(2) solid #0e88f7;
  box-shadow: 0 0 vw(20) 0 rgba(#0e88f7, 0.25);

  @include xxl {
    margin-left: xxl-vw(20);

    width: xxl-vw(160);
    height: xxl-vw(50);

    border-radius: xxl-vw(20);
    border: xxl-vw(2) solid #0e88f7;
    box-shadow: 0 0 xxl-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include lg {
    margin-left: lg-vw(20);

    width: lg-vw(160);
    height: lg-vw(50);

    border-radius: lg-vw(20);
    border: lg-vw(2) solid #0e88f7;
    box-shadow: 0 0 lg-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include md {
    margin-left: 0;
    align-self: flex-end;

    width: md-vw(160);
    height: md-vw(50);

    border-radius: md-vw(20);
    border: md-vw(2) solid #0e88f7;
    box-shadow: 0 0 md-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include sm {
    margin-left: sm-vw(20);

    width: sm-vw(160);
    height: sm-vw(50);

    border-radius: sm-vw(20);
    border: sm-vw(2) solid #0e88f7;
    box-shadow: 0 0 sm-vw(20) 0 rgba(#0e88f7, 0.25);
  }
}

.amount {
  width: vw(60);

  font-weight: 500;
  font-size: vw(33);
  color: #0e88f7;

  @include xxl {
    width: xxl-vw(60);

    font-size: xxl-vw(33);
  }

  @include lg {
    width: lg-vw(60);

    font-size: lg-vw(33);
  }

  @include md {
    width: md-vw(60);

    font-size: md-vw(33);
  }

  @include sm {
    width: sm-vw(60);

    font-size: sm-vw(33);
  }
}

.btn {
  display: flex;
  align-items: center;
  justify-content: center;

  width: vw(35);
  height: 100%;
  border-radius: vw(20);

  @include xxl {
    width: xxl-vw(35);

    border-radius: xxl-vw(20);
  }

  @include lg {
    width: lg-vw(35);

    border-radius: lg-vw(20);
  }

  @include md {
    width: md-vw(35);

    border-radius: md-vw(20);
  }

  @include sm {
    width: sm-vw(35);

    border-radius: sm-vw(20);
  }

  &:nth-of-type(1) {
    padding-left: vw(10);

    @include xxl {
      padding-left: xxl-vw(10);
    }

    @include lg {
      padding-left: lg-vw(10);
    }

    @include md {
      padding-left: md-vw(10);
    }

    @include sm {
      padding-left: sm-vw(10);
    }
  }
  &:nth-of-type(2) {
    padding-right: vw(10);

    @include xxl {
      padding-right: xxl-vw(10);
    }

    @include lg {
      padding-right: lg-vw(10);
    }

    @include md {
      padding-right: md-vw(10);
    }

    @include sm {
      padding-right: sm-vw(10);
    }
  }
}

.arrow {
  width: vw(14);
  height: vw(14);
  border-left: vw(2) solid #000;
  border-top: vw(2) solid #000;

  @include xxl {
    width: xxl-vw(14);
    height: xxl-vw(14);
    border-left: xxl-vw(2) solid #000;
    border-top: xxl-vw(2) solid #000;
  }

  @include lg {
    width: lg-vw(14);
    height: lg-vw(14);
    border-left: lg-vw(2) solid #000;
    border-top: lg-vw(2) solid #000;
  }

  @include md {
    width: md-vw(14);
    height: md-vw(14);
    border-left: md-vw(2) solid #000;
    border-top: md-vw(2) solid #000;
  }

  @include sm {
    width: sm-vw(14);
    height: sm-vw(14);
    border-left: sm-vw(2) solid #000;
    border-top: sm-vw(2) solid #000;
  }
}

.arrow_minus {
  transform: rotate(-45deg);
}

.arrow_plus {
  transform: rotate(135deg);
}

.product-card__price {
  font-weight: 500;
  font-size: vw(20);
  color: #fff;

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }

  @include md {
    font-size: md-vw(20);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}

.product-card__text {
  font-weight: 500;
  font-size: vw(16);
  color: #fff;

  @include xxl {
    font-size: xxl-vw(16);
  }

  @include lg {
    font-size: lg-vw(16);
  }

  @include md {
    font-size: md-vw(16);
  }

  @include sm {
    font-size: sm-vw(16);
  }
}
</style>
