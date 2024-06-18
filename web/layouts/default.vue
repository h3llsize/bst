<script lang="ts" setup>
import Footer from '#/components/layouts/footer.vue'
import Header from '#/components/layouts/header.vue'
import CategoryBar from '#/components/layouts/category-bar.vue'
import type { CartProduct } from '#/types/models'

const hasDropdown = computed(() => useRoute().meta.hasDropdown)

const { data } = await useAsyncData('initialize', async (app) => {
  const subdomain = app?.ssrContext?.event.context.subdomain
  const host = app?.ssrContext?.event.context.hostname
  const cart = (useCookie(CART_KEY).value ?? []) as CartProduct[]

  return { subdomain, host, cart }
})

if (data.value)
  useStore().$patch(data.value)
</script>

<template>
  <NuxtLoadingIndicator
    :height="4"
    :duration="3000"
    :throttle="400"
  />
  <Header />
  <CategoryBar v-show="hasDropdown" />
  <main>
    <slot />
  </main>
  <Footer />
</template>
