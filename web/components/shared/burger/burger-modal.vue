<script lang='ts' setup>
import { useRoute } from 'vue-router'
import { RouteNames, RoutePaths } from '#/types/routes'
import { useStore } from '#/store/bst.store'

const store = useStore()
const route = useRoute()

const model = defineModel()

const categories = computed(() => store.categories)

const navItems = [
  {
    name: 'каталог',
    route: RouteNames.Catalog,
  },
  {
    name: 'контакты',
    route: RouteNames.Contacts,
  },
  {
    name: 'о нас',
    route: RouteNames.About,
  },
  {
    name: 'обратная связь',
    route: RouteNames.Feedback,
  },
  {
    name: 'корзина',
    route: RouteNames.Cart,
  },
]

function handleClick(event: MouseEvent) {
  if ((event.target as HTMLDialogElement).className === 'burger__modal modal active')
    model.value = false
}
</script>

<template>
  <dialog
    class="burger__modal modal"
    :class="{ active: model }"
    @click="handleClick"
  >
    <div class="burger__content">
      <ul class="burger__list">
        <li
          v-for="page in navItems"
          :key="page.route"
          class="burger__item"
          @click.stop="model = false"
        >
          <NuxtLink
            class="burger__link"
            :class="{ is_active: page.route === route.name }"
            :to="{ name: page.route }"
          >
            {{ page.name }}
          </NuxtLink>
        </li>
      </ul>
      <div class="burger__line" />
      <ul class="burger__list">
        <ClientOnly>
          <li
            v-for="category in categories"
            :key="category.id"
            class="burger__item"
            @click.stop="model = false"
          >
            <NuxtLink
              class="burger__link"
              :class="{ is_active: category.slug === route.params.id }"
              :to="`${RoutePaths.Maincategory}/${category.slug}`"
            >
              {{ category.name }}
            </NuxtLink>
          </li>
        </ClientOnly>
      </ul>
    </div>
  </dialog>
</template>

<style lang='scss' scoped>
.burger__content {
  display: flex;
  flex-direction: column;
  gap: sm-vw(40);

  background: #fff;
  padding: sm-vw(40) sm-vw(60);
  border-radius: sm-vw(20);
}

.burger__line {
  width: 100%;
  height: sm-vw(2);
  background: #000;
}

.burger__list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: sm-vw(40);
}

.burger__link {
  font-size: sm-vw(24);

  &.is_active {
    color: #0e88f7;
  }
}
</style>
