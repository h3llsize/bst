<script lang='ts' setup>
import CatalogCategory from "#/components/pages/catalog/catalog-category.vue";

import Search from "#/components/shared/search.vue";
import Pagination from "#/components/shared/pagination.vue";

import { Services } from "#/utils/api";
import type { SubCategory } from "#/types/models";
import Skeleton from "~/components/shared/skeleton.vue";
import { RouteNames } from "~/types/routes";

const catalogSubCategories = ref<SubCategory[] | null>(null)
const activePage = ref<number>(1)
const pages = ref<number>(0)
const searchValue = ref<string>('')
const showLoader = ref<boolean>(false)

async function loadSubCategories() {
  showLoader.value = true
  await Services.getStartData(activePage.value, searchValue.value).then((data) => {
    catalogSubCategories.value = data?.content
     pages.value = data?.totalPages
  }).finally(() => showLoader.value = false)
}

function paginationClick(page: number) {
  activePage.value = page
  loadSubCategories()
}

function handleSearch(value: string) {
  searchValue.value = value
  activePage.value = 1
  loadSubCategories()
}

void (await useLoadMeta({ type: 'catalog' })).applySeoMeta()

await loadSubCategories()

definePageMeta({
  hasDropdown: true,
})
</script>

<template>
  <section class="catalog">
    <div class="container">
      <Search
        class="catalog__search"
        @search="handleSearch"
      />
      <div v-if="showLoader">
        <Skeleton style="height: 28px; width: 350px; margin-left: 24px" />
        <div
          v-for="i in 5"
          :key="i"
          class="skeleton-card"
        >
          <Skeleton style="height: 24px; width: 60%" />
          <Skeleton style="height: 40px; width: 150px; max-width: 150px" />
        </div>
        <Skeleton style="height: 28px; width: 350px; margin-left: 24px; margin-top: 50px" />
        <div
          v-for="i in 5"
          :key="i"
          class="skeleton-card"
        >
          <Skeleton style="height: 24px; width: 60%" />
          <Skeleton style="height: 40px; width: 150px; max-width: 150px" />
        </div>
      </div>
      <div
        v-else
        class="catalog__list"
      >
        <CatalogCategory
          v-for="category in catalogSubCategories"
          :key="`${category.id}${searchValue}`"
          :category="category"
        />
        <p
          v-if="!showLoader && !catalogSubCategories?.length"
          class="not-found"
        >
          Ничего не найдено
        </p>
      </div>
      <Pagination
        v-if="pages && !showLoader"
        class="catalog__pagination"
        :pages="pages"
        :active="activePage"
        :click="paginationClick"
      />
    </div>
  </section>
</template>

<style lang='scss' scoped>
.container {
  display: flex;
  flex-direction: column;
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

.catalog__search {
  margin-bottom: vw(50);

  @include xxl {
    margin-bottom: xxl-vw(50);
  }

  @include lg {
    margin-bottom: lg-vw(50);
  }

  @include md {
    margin-bottom: md-vw(50);
  }
}

.catalog__list {
  display: flex;
  flex-direction: column;
  gap: vw(50);

  @include xxl {
    gap: xxl-vw(50);
  }

  @include lg {
    gap: lg-vw(50);
  }

  @include md {
    gap: md-vw(50);
  }

  @include sm {
    gap: sm-vw(50);
  }
}

.catalog__pagination {
  margin-top: vw(50);
  align-self: center;

  @include xxl {
    margin-top: xxl-vw(50);
  }

  @include lg {
    margin-top: lg-vw(50);
  }

  @include md {
    margin-top: md-vw(50);
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

  @include md {
    font-size: md-vw(32);
  }

  @include sm {
    font-size: sm-vw(32);
  }
}
</style>
