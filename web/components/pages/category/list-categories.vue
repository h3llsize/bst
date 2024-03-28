<script lang='ts' setup>
import CategoryCard from './category-card.vue'
import type { SubCategory } from '#/types/models'
import type { RouteNames } from '#/types/routes'

interface Props {
  items: SubCategory[]
  type: RouteNames.Category | RouteNames.SubCategory
}

defineProps<Props>()
</script>

<template>
  <ul class="list-categories">
    <CategoryCard
      v-for="item in items"
      :key="item.id"
      :item="item"
      :type="type"
    />
  </ul>
</template>

<style lang='scss' scoped>
.list-categories {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: vw(30);

  @include xxl {
    grid-gap: xxl-vw(30);
  }

  @include lg {
    grid-gap: lg-vw(30);
  }
}
</style>
