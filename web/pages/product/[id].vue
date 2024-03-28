<script lang='ts' setup>
import ProductOptions from "#/components/shared/product/product-options.vue";
import ProductAbout from "#/components/shared/product/product-about.vue";
import ProductSimiliar from "#/components/shared/product/product-similiar.vue";
import { CatalogPostDTO, Services } from "#/utils/api";
import { RouteNames } from "#/types/routes";
import type { Product } from "#/types/models";

const route = useRoute()
const router = useRouter()
const store = useStore()

const slug = Array.isArray(route.params.id) ? route.params.id[0] : route.params.id

const product = ref<Product>({
  id: 0,
  slug: '',
  name: '',
  price: 0,
  options: [],
  categoryId: 0,
  pathToImage: null,
  description: null,
  manufacturing: null,
  advantages: null,
  range: null,
})

const products = ref<Product[]>([])

const feedbackModal = ref<boolean>(false)

async function loadProduct() {
  try {
    const data = await Services.getProductBySlug(slug)

    product.value = data

    return data
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

async function loadProducts() {
  const dto = new CatalogPostDTO()
  dto.categoryId = product.value.categoryId
  dto.page = 0
  dto.query = null
  dto.filters = null

  try {
    const data = await Services.getProducts(dto)
    products.value = data.content.filter(el => el.id !== product.value.id)
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

function changeAmount(amount: number) {
  store?.addProduct(product.value.id, store?.getProduct(product.value.id).amount + amount)
}

void (await useLoadMeta({ slug: slug, type: RouteNames.Product })).applySeoMeta()

await loadProduct()
await loadProducts()

definePageMeta({
  hasDropdown: true,
})
</script>

<template>
  <section class="product">
    <div class="container">
      <div
        class="product__info"
        :class="{ withoutImage: !product.pathToImage }"
      >
        <img
          v-if="product.pathToImage"
          class="product__img"
          :src="product.pathToImage ? `https://bst-sl.ru/${product.pathToImage}` : ''"
          :alt="product.name"
        >

        <div class="product__right">
          <h2 class="product__heading">
            {{ product.name }}
          </h2>

          <ProductOptions
            :options="product.options.concat([{ name: 'Цена', param: `${product.price > 299 ? product.price : 'договорная'} &#8381;` }])"
            class="product__options"
          />

          <div class="product__btn-box">
            <button
              v-if="store?.getProduct(product.id).amount"
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
                {{ store?.getProduct(product.id).amount }}
              </span>
              <button
                class="btn"
                @click.prevent="changeAmount(1)"
              >
                <span class="arrow arrow_plus" />
              </button>
            </button>

            <button
              v-else
              class="product-card__add"
              @click.prevent="changeAmount(1)"
            >
              <span class="product-card__text">
                Добавить в корзину
              </span>
            </button>

            <button
              class="product-card__add product-card__feedback"
              @click="feedbackModal = true"
            >
              <span class="product-card__text">
                Оставить заявку
              </span>
            </button>
          </div>
        </div>
      </div>

      <ProductSimiliar :products="products" />

      <ProductAbout
        :description="product.description"
        :manufacturing="product.manufacturing"
        :advantages="product.advantages"
        :range="product.range"
      />
    </div>

    <FeedbackModal v-model="feedbackModal" />
  </section>
</template>

<style lang='scss' scoped>
.product__heading {
  font-weight: 600;
  font-size: vw(32);

  margin-bottom: vw(30);

  @include xxl {
    font-size: xxl-vw(32);

    margin-bottom: xxl-vw(30);
  }

  @include lg {
    font-size: lg-vw(32);

    margin-bottom: lg-vw(30);
  }

  @include sm {
    font-size: sm-vw(32);

    margin-bottom: sm-vw(30);
  }
}

.product__info {
  display: grid;
  grid-template-columns: vw(500) auto;
  grid-gap: vw(50);

  margin-bottom: vw(50);

  @include xxl {
    grid-template-columns: xxl-vw(500) auto;
    grid-gap: xxl-vw(50);

    margin-bottom: xxl-vw(50);
  }

  @include lg {
    display: flex;
    flex-direction: column;
    gap: lg-vw(50);

    margin-bottom: lg-vw(50);
  }

  @include sm {
    display: flex;
    flex-direction: column;
    gap: sm-vw(50);

    margin-bottom: sm-vw(50);
  }

  &.withoutImage {
    grid-template-columns: 0.7fr;

    @include md {
      grid-template-columns: 1fr;
    }
  }
}

.product__img {
  flex-shrink: 0;

  width: 100%;
  height: 100%;
  object-fit: cover;
  background: #d3d3d3;
  border-radius: vw(10);
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.05);

  @include xxl {
    border-radius: xxl-vw(10);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.05);
  }

  @include lg {
    height: lg-vw(400);
    border-radius: lg-vw(10);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.05);
  }

  @include sm {
    height: sm-vw(400);
    border-radius: sm-vw(10);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#000, 0.05);
  }
}

.product__options {
  margin-bottom: vw(30);

  @include xxl {
    margin-bottom: xxl-vw(30);
  }

  @include lg {
    margin-bottom: lg-vw(30);
  }

  @include sm {
    margin-bottom: sm-vw(30);
  }
}

.product__right {
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  width: 100%;
  height: 100%;
}

.product-card__sum {
  font-weight: 500;
  font-size: vw(26);
  margin-right: vw(30);

  @include xxl {
    font-size: xxl-vw(26);
    margin-right: xxl-vw(30);
  }

  @include lg {
    font-size: lg-vw(26);
    margin-right: lg-vw(30);
  }

  @include sm {
    font-size: sm-vw(26);
    margin-right: sm-vw(30);
  }
}

.product__btn-box {
  display: flex;
  align-items: center;
  gap: vw(50);

  @include xxl {
    gap: xxl-vw(50);
  }

  @include lg {
    gap: lg-vw(50);
  }

  @include sm {
    gap: sm-vw(50);
  }
}

.product-card__add {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  width: calc(50% - vw(30));
  height: vw(60);
  border-radius: vw(40);
  background: #0e88f7;
  box-shadow: 0 0 vw(30) rgba(#0e88f7, 0.25);

  @include xxl {
    width: calc(50% - xxl-vw(30));
    height: xxl-vw(60);
    border-radius: xxl-vw(40);
    box-shadow: 0 0 xxl-vw(30) rgba(#0e88f7, 0.25);
  }

  @include lg {
    width: calc(50% - lg-vw(30));
    height: lg-vw(60);
    border-radius: lg-vw(40);
    box-shadow: 0 0 lg-vw(30) rgba(#0e88f7, 0.25);
  }

  @include sm {
    width: calc(50% - sm-vw(30));
    height: sm-vw(60);
    border-radius: sm-vw(40);
    box-shadow: 0 0 sm-vw(30) rgba(#0e88f7, 0.25);
  }
}

.product-card__feedback {
  background: transparent;
  border: vw(2) solid #0e88f7;

  @include xxl {
    border: xxl-vw(2) solid #0e88f7;
  }

  @include lg {
    border: lg-vw(2) solid #0e88f7;
  }

  @include sm {
    border: sm-vw(2) solid #0e88f7;
  }
}

.product-card__count {
  display: flex;
  align-items: center;
  justify-content: space-between;

  width: calc(50% - vw(30));
  height: vw(60);

  border-radius: vw(40);
  border: vw(2) solid #0e88f7;
  box-shadow: 0 0 vw(20) 0 rgba(#0e88f7, 0.25);

  @include xxl {
    width: calc(50% - xxl-vw(30));
    height: xxl-vw(60);

    border-radius: xxl-vw(40);
    border: xxl-vw(2) solid #0e88f7;
    box-shadow: 0 0 xxl-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include lg {
    width: calc(50% - lg-vw(30));
    height: lg-vw(60);

    border-radius: lg-vw(40);
    border: lg-vw(2) solid #0e88f7;
    box-shadow: 0 0 lg-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include sm {
    width: calc(50% - sm-vw(30));
    height: sm-vw(60);

    border-radius: sm-vw(40);
    border: sm-vw(2) solid #0e88f7;
    box-shadow: 0 0 sm-vw(20) 0 rgba(#0e88f7, 0.25);
  }
}

.amount {
  width: vw(60);

  font-weight: 500;
  font-size: vw(40);
  color: #000;

  @include xxl {
    width: xxl-vw(60);

    font-size: xxl-vw(40);
  }

  @include lg {
    width: lg-vw(60);

    font-size: lg-vw(40);
  }

  @include sm {
    width: sm-vw(60);

    font-size: sm-vw(40);
  }
}

.btn {
  display: flex;
  align-items: center;
  justify-content: center;

  width: vw(60);
  height: 100%;
  border-radius: vw(30);

  @include xxl {
    width: xxl-vw(60);

    border-radius: xxl-vw(30);
  }

  @include lg {
    width: lg-vw(60);

    border-radius: lg-vw(30);
  }

  @include sm {
    width: sm-vw(60);

    border-radius: sm-vw(30);
  }

  &:nth-of-type(1) {
    padding-left: vw(20);

    @include xxl {
      padding-left: xxl-vw(20);
    }

    @include lg {
      padding-left: lg-vw(20);
    }

    @include sm {
      padding-left: sm-vw(20);
    }
  }
  &:nth-of-type(2) {
    padding-right: vw(20);

    @include xxl {
      padding-right: xxl-vw(20);
    }

    @include lg {
      padding-right: lg-vw(20);
    }

    @include sm {
      padding-right: sm-vw(20);
    }
  }
}

.arrow {
  width: vw(20);
  height: vw(20);
  border-left: vw(4) solid #000;
  border-top: vw(4) solid #000;

  @include xxl {
    width: xxl-vw(20);
    height: xxl-vw(20);
    border-left: xxl-vw(4) solid #000;
    border-top: xxl-vw(4) solid #000;
  }

  @include lg {
    width: lg-vw(20);
    height: lg-vw(20);
    border-left: lg-vw(4) solid #000;
    border-top: lg-vw(4) solid #000;
  }

  @include sm {
    width: sm-vw(20);
    height: sm-vw(20);
    border-left: sm-vw(4) solid #000;
    border-top: sm-vw(4) solid #000;
  }
}

.arrow_minus {
  transform: rotate(-45deg);
}

.arrow_plus {
  transform: rotate(135deg);
}

.product-card__text {
  font-weight: 500;
  font-size: vw(22);
  color: #fff;

  @include xxl {
    font-size: xxl-vw(22);
  }

  @include lg {
    font-size: lg-vw(22);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}

.product-card__feedback .product-card__text {
  font-weight: 600;
  color: #062120;
}
</style>
