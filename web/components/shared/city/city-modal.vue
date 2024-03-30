<script lang='ts' setup>
import type { City } from '#/types/models'

interface Props {
  cities: City[]
  subdomain: string
}

defineProps<Props>()

const model = defineModel()

// const route = useRoute()

// function getHref(domain: string) {
//   let url = ''
//   const parts = useCookie('host').value?.split('.') ?? []
//   const pathname = route.path
//
//   if (parts.length > 2) {
//     parts[0] = domain
//     url = `${parts.join('.')}${pathname}`
//   }
//   else {
//     url = `${domain}.${parts.join('.')}${pathname}`
//   }
//   return url
// }

function handleClick(event: MouseEvent) {
  if ((event.target as HTMLDialogElement).className === 'city__modal modal active')
    model.value = false
}
</script>

<template>
  <dialog
    class="city__modal modal"
    :class="{ active: model }"
    @click="handleClick"
  >
    <ul class="city__list">
      <li
        v-for="city in cities"
        :key="city.domain"
        class="city__item"
        @click.stop="handleClick"
      >
        <a
          v-if="city.domain"
          class="city__link"
          :class="{ active: city.domain === subdomain }"
          :href="`https://${city.domain}.bst-sl.ru`"
        >
          {{ city.name }}
        </a>
      </li>
    </ul>
  </dialog>
</template>

<style lang='scss' scoped>
  .city__list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: vw(25);

  padding: vw(40);
  background: #fff;
  border-radius: vw(20);

  @include xxl {
    grid-gap: xxl-vw(25);

    padding: xxl-vw(40);
    border-radius: xxl-vw(20);
  }

  @include lg {
    grid-gap: lg-vw(25);

    padding: lg-vw(40);
    border-radius: lg-vw(20);
  }

  @include md {
    grid-gap: md-vw(25);

    padding: md-vw(40);
    border-radius: md-vw(20);
  }

  @include sm {
    grid-gap: sm-vw(25);

    padding: sm-vw(40);
    border-radius: sm-vw(20);
  }
}

.city__link {
  font-weight: 500;
  font-size: vw(25);

  @include xxl {
    font-size: xxl-vw(25);
  }

  @include lg {
    font-size: lg-vw(25);
  }

  @include md {
    font-size: md-vw(25);
  }

  @include sm {
    font-size: sm-vw(25);
  }

  &.active {
    color: #0e88f7;
  }
}
</style>
