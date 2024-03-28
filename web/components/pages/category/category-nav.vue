<script lang='ts' setup>
import type { MainCategory } from '#/types/models'
import type { HoverData } from '#/types/common'

import { RoutePaths } from '#/types/routes'

const props = defineProps<{ items: MainCategory[] }>()
const emit = defineEmits<{ navHover: [data: HoverData] }>()

const items = computed<MainCategory[] | null>(() => {
  if (!props.items)
    return null

  return props.items.slice(0, 6)
})

function handleHover(state: boolean, slug: string) {
  emit('navHover', { state, slug })
}
</script>

<template>
  <div class="category-nav">
    <div class="container">
      <ul class="category-nav__list">
        <li
          v-for="item in items"
          :key="item.id"
          class="category-nav__item"
          @mouseover="handleHover(true, item.slug)"
          @mouseout="handleHover(false, item.slug)"
        >
          <NuxtLink
            :to="`${RoutePaths.Maincategory}/${item.slug}`"
          >
            {{ item.name }}
          </NuxtLink>
        </li>
      </ul>
    </div>
  </div>
</template>

<style lang='scss' scoped>
.category-nav {
  position: relative;
  z-index: 9;

  min-height: vw(75);

  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000000, 0.05);

  @include xxl {
    min-height: xxl-vw(75);

    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000000, 0.05);
  }

  @include lg {
    min-height: lg-vw(75);

    box-shadow: 0 0 lg-vw(30) 0 rgba(#000000, 0.05);
  }

  @include sm {
    min-height: sm-vw(75);

    box-shadow: 0 0 sm-vw(30) 0 rgba(#000000, 0.05);
  }
}

.container {
  display: flex;

  height: vw(75);

  @include xxl {
    height: xxl-vw(75);
  }

  @include lg {
    height: lg-vw(75);
  }

  @include sm {
    height: sm-vw(75);
  }
}

.category-nav__list {
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
    display: none;
  }
}

.category-nav__item {
  display: flex;
  align-items: center;
  justify-content: center;

  height: 100%;

  font-size: vw(22);

  cursor: pointer;

  @include xxl {
    font-size: xxl-vw(22);
  }

  @include lg {
    font-size: lg-vw(22);
  }
}
</style>
