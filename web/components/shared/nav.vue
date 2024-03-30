<script lang="ts" setup>
import { RouteNames } from '#/types/routes'

defineProps<{ direction: 'row' | 'column' }>()

const route = useRoute()

const navItems = [{
  name: 'каталог',
  route: RouteNames.Catalog,
}, {
  name: 'контакты',
  route: RouteNames.Contacts,
}, {
  name: 'о нас',
  route: RouteNames.About,
}]
</script>

<template>
  <nav class="nav">
    <ul
      class="nav__list"
      :style="{ flexDirection: direction }"
    >
      <li
        v-for="item in navItems"
        :key="item.route"
        class="nav__item"
      >
        <NuxtLink
          :to="{ name: item.route }"
          :class="{ is_active: item.route === route.name }"
          class="nav__link"
        >
          {{ item.name }}
        </NuxtLink>
      </li>
    </ul>
  </nav>
</template>

<style lang='scss' scoped>
.nav__list {
  display: flex;
  align-items: center;
  gap: vw(40);

  @include xxl {
    gap: xxl-vw(40);
  }

  @include lg {
    gap: lg-vw(40);
  }

  @include sm {
    gap: sm-vw(40);
  }
}

.nav__item {
  font-size: vw(24);
  line-height: 100%;

  @include xxl {
    font-size: xxl-vw(24);
  }

  @include lg {
    font-size: lg-vw(24);
  }

  @include sm {
    font-size: sm-vw(24);
  }
}

.nav__link.is_active {
  color: #0e88f7;
}
</style>
