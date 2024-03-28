<script lang='ts' setup>
import Filter from '#/components/shared/filter.vue'
import ProductCard from '#/components/shared/product/product-card.vue'
import Pagination from '#/components/shared/pagination.vue'
import { CatalogPostDTO, Services } from '#/utils/api'
import { RouteNames } from '#/types/routes'
import type { Product, SubCategory } from '#/types/models'
import type { FilterCategory } from '#/types/common'
import Skeleton from '~/components/shared/skeleton.vue'

const route = useRoute()
const router = useRouter()

const slug = Array.isArray(route.params.id) ? route.params.id[0] : route.params.id
const modal = ref<boolean>(false)

function handleFilterClick(event: MouseEvent) {
  if ((event.target as HTMLDialogElement).className === 'filter-box modal active')
    modal.value = false
}

const subcategory = ref<SubCategory>({
  id: 0,
  name: '',
  slug: '',
  imagePath: '',
})

const products = ref<Product[]>([])

const filters = ref<FilterCategory[]>([])

const activePage = ref<number>(1)
const pages = ref<number>(0)
const showLoader = ref<boolean>(true)

async function loadSubCategory() {
  subcategory.value = {
    id: 0,
    name: '',
    slug: '',
    imagePath: '',
  }

  products.value = []
  filters.value = []

  activePage.value = 1
  pages.value = 0
  showLoader.value = false

  try {
    subcategory.value = await Services.getSubCategory(slug)
    useSeoMeta({
      title: `${subcategory.value.name}`,
    })
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

async function loadProducts() {
  const dto = new CatalogPostDTO()

  dto.categoryId = subcategory.value.id
  dto.page = activePage.value - 1
  dto.query = ''
  dto.filters = getOptions()

  showLoader.value = true

  try {
    const data = await Services.getProducts(dto)
    products.value = data.content as unknown as Product[]
    pages.value = data.totalPages
    activePage.value = data.page
    showLoader.value = false
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

async function loadFilters() {
  try {
    filters.value = await Services.getFilters(subcategory.value.id)
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

function paginationClick(page: number) {
  activePage.value = page
  loadProducts()
}

function getOptions() {
  const list: { optionsId: number[] }[] = []
  if (Array.isArray(filters.value)) {
    filters.value.forEach((category) => {
      const checked = category.categoryOptions
        .filter(option => option.value)
        .map(option => option.id)

      if (checked.length) {
        list.push({
          optionsId: checked,
        })
      }
    })

    return list
  }
  else {
    return null
  }
}

void (await useLoadMeta({ slug, type: RouteNames.SubCategory })).applySeoMeta()

await loadSubCategory()
await loadFilters()
await loadProducts()

definePageMeta({
  hasDropdown: true,
})
</script>

<template>
  <section class="subcategory">
    <div
      class="container"
      :class="{ withoutFilter: filters.length === 0 }"
    >
      <dialog
        v-show="filters.length"
        class="filter-box modal"
        :class="{ active: modal }"
        @click="handleFilterClick"
      >
        <Filter
          v-model:categories="filters"
          class="filter"
          @submit="loadProducts"
          @click.stop="handleFilterClick"
        />
      </dialog>
      <div class="content-box">
        <h2 class="subcategory__heading">
          {{ subcategory.name }}
        </h2>

        <button
          v-if="filters.length"
          class="subcategory__filter-modal"
          @click.prevent="modal = true"
        >
          Фильтры
        </button>

        <div class="subcategory__list">
          <ProductCard
            v-for="product in products"
            v-show="!showLoader && products.length"
            :id="product.id"
            :key="product.id"
            :name="product.name"
            :price="product.price"
            :slug="product.slug"
            :full="false"
          />
          <div v-show="showLoader">
            <div
              v-for="i in 5"
              :key="i"
              class="skeleton-card"
            >
              <Skeleton style="height: 24px; width: 60%" />
              <Skeleton style="height: 40px; width: 150px; max-width: 150px" />
            </div>
          </div>
          <p
            v-show="!showLoader && !products.length"
            class="not-found"
          >
            Ничего не найдено
          </p>
        </div>

        <Pagination
          v-if="!showLoader && products.length"
          class="subcategory__pagination"
          :pages="pages"
          :active="activePage"
          :click="paginationClick"
        />
      </div>
    </div>
  </section>
</template>

<style lang='scss' scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-gap: vw(30);

  &.withoutFilter {
    grid-template-columns: 1fr;
  }

  @include xxl {
    grid-gap: xxl-vw(30);
  }

  @include lg {
    grid-gap: lg-vw(30);
  }

  @include sm {
    grid-template-columns: 1fr;
  }
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

.subcategory__heading {
  font-weight: 600;
  font-size: vw(36);

  margin-bottom: vw(40);

  @include xxl {
    font-size: xxl-vw(36);

    margin-bottom: xxl-vw(40);
  }

  @include lg {
    font-size: lg-vw(36);

    margin-bottom: lg-vw(40);
  }

  @include sm {
    font-size: sm-vw(36);

    margin-bottom: sm-vw(10);
  }
}

.content-box {
  display: flex;
  flex-direction: column;
}

.subcategory__search {
  margin-bottom: vw(50);

  @include xxl {
    margin-bottom: xxl-vw(50);
  }

  @include lg {
    margin-bottom: lg-vw(50);
  }

  @include sm {
    margin-bottom: sm-vw(50);
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

.subcategory__pagination {
  margin-top: vw(50);
  align-self: center;

  @include xxl {
    margin-top: xxl-vw(50);
  }

  @include lg {
    margin-top: lg-vw(50);
  }

  @include sm {
    margin-top: sm-vw(50);
  }
}

.loader-box {
  display: flex;
  align-items: center;
  justify-content: center;
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

.filter-box {
  position: relative;
  display: block;
  background: none;
  z-index: 0;

  height: auto;

  @include sm {
    display: none;
  }

  &.active {
    position: fixed !important;
    z-index: 20;
    background: rgba(#000, 0.3);
  }
}

.subcategory__filter-modal {
  display: none;
  align-self: flex-start;

  height: auto;
  padding: sm-vw(15) sm-vw(30);
  border-radius: sm-vw(30);
  background: #0e88f7;
  box-shadow: 0 0 sm-vw(20) 0 rgba(#0e88f7, 0.3);

  margin-bottom: sm-vw(40);

  font-weight: 600;
  font-size: sm-vw(20);
  color: white;

  @include sm {
    display: block;
  }
}

.filter {
  @include sm {
    width: 70% !important;
    height: 90% !important;
    overflow-y: scroll;
  }
}
</style>
