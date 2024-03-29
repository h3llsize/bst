<script lang="ts" setup>
import CategoryNav from '#/components/pages/category/category-nav.vue'
import CategoryDropdown from '#/components/pages/category/category-dropdown.vue'

import type { HoverData } from '#/types/common'
import { Services } from '#/utils/api'
import type { MainCategory } from '#/types/models'

const store = useStore()

const dropdownData = ref<HoverData>({
  state: false,
  slug: '',
})

const categories = ref<MainCategory[]>([])

function navHover(data: HoverData) {
  dropdownData.value = data
}

async function loadCategories() {
  const data = await Services.getMainCategories()

  categories.value = data
  store.categories = data
}

await loadCategories()
</script>

<template>
  <div class="category-bar">
    <CategoryNav
      :items="categories"
      @nav-hover="navHover"
    />
    <CategoryDropdown
      :categories="categories"
      :data="dropdownData"
    />
  </div>
</template>

<style lang="scss" scoped>
.category-bar {
  @include sm {
    display: none;
  }
}
</style>
