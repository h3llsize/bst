<script lang='ts' setup>
import { Services } from '#/utils/api'

import type { Category, MainCategory } from '#/types/models'
import type { HoverData } from '#/types/common'

import { RoutePaths } from '#/types/routes'

const props = defineProps<{ data: HoverData, categories: MainCategory[] }>()

const items = ref<Category[]>([])
const showDropdown = ref<boolean>(false)
// const loading = ref<boolean>(false)

const actualCategories = ref<{ data: Category[], slug: string }[]>([])

function handleHover(state: boolean) {
  showDropdown.value = state
}

// function setCategories() {
//   loading.value = true
//   for (const i of props.categories) {
//       Services.getCategories(i.slug).then(data => {
//         actualCategories.value.push({ slug: i.slug, data: data?.slice(0, 10) })
//       }).finally(() => {
//         loading.value = false
//       })
//   }
//   loading.value = false
// }

const show = computed(() => {
  return props.data.state || showDropdown.value
})

watch(() => props.data, () => {
  const index = props.categories.findIndex(item => item.slug === props.data.slug)
  items.value = props.categories[index]?.categories
})
</script>

<template>
  <div
    v-show="show"
    class="dropdown"
    @mouseover="handleHover(true)"
    @mouseout="handleHover(false)"
  >
    <div class="container">
      <ul class="dropdown__list">
        <li
          v-for="category in items"
          :key="category.id"
          class="dropdown__category"
        >
          <NuxtLink
            class="category__heading"
            :to="`${RoutePaths.Category}/${category.slug}`"
            @click="showDropdown = false"
          >
            {{ category.name }}
          </NuxtLink>
          <NuxtLink
            v-for="subcategory in category.catalogSubCategories.slice(0, 5)"
            :key="subcategory.id"
            class="subcategory__heading"
            :to="`${RoutePaths.SubCategory}/${subcategory.slug}`"

            @click="showDropdown = false"
          >
            {{ subcategory.name }}
          </NuxtLink>
        </li>
      </ul>
    </div>
  </div>
</template>

<style lang='scss' scoped>
.dropdown {
  position: absolute;
  z-index: 8;

  width: 100%;

  background: #fff;
}

.dropdown__list {
  // display: flex;
  // flex-direction: column;
  // flex-wrap: wrap;
  // gap: vw(10);
  //
  // max-width: 100%;
  // max-height: vw(500);
  // padding-bottom: vw(50);
  //
  // @include xxl {
  //   gap: xxl-vw(10);
  //
  //   max-height: xxl-vw(550);
  //   padding: xxl-vw(50) 0;
  // }

  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-gap: vw(50);

  width: 100%;
  padding: vw(50) 0;

  @include xxl {
    grid-template-columns: repeat(4, 1fr);
    grid-gap: xxl-vw(50);

    padding: xxl-vw(50) 0;
  }

  @include lg {
    grid-template-columns: repeat(3, 1fr);
    grid-gap: lg-vw(50);

    padding: lg-vw(50) 0;
  }
}

.category__heading {
  font-weight: 600;
  font-size: vw(20);

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }
}

.subcategory__heading {
  font-size: vw(18);
  line-height: 150%;

  @include xxl {
    font-size: xxl-vw(18);
  }

  @include lg {
    font-size: lg-vw(18);
  }
}

.dropdown__category {
  display: flex;
  flex-direction: column;
  gap: vw(10);

  @include xxl {
    gap: xxl-vw(10);
  }

  @include lg {
    gap: lg-vw(10);
  }
}

.dropdown__item {
  font-size: vw(18);

  @include xxl {
    font-size: xxl-vw(18);
  }

  @include lg {
    font-size: lg-vw(18);
  }

  &.heading {
    font-weight: 700;

    margin-top: vw(30);

    @include xxl {
      margin-top: xxl-vw(30);
    }

    @include lg {
      margin-top: lg-vw(30);
    }
  }
}
</style>
