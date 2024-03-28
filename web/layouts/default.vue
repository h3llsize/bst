<script lang="ts" setup>
import Footer from '#/components/layouts/footer.vue'
import Header from '#/components/layouts/header.vue'
import CategoryBar from '#/components/layouts/category-bar.vue'
const hasDropdown = computed(() => useRoute().meta.hasDropdown)

await useAsyncData('subdomain', async (app) => {
  const subdomain = app?.ssrContext?.event.context.subdomain
  const hostname = app?.ssrContext?.event.context.hostname

  useStore().setDomainData(
    subdomain,
    hostname
  )

  return Promise.resolve()
})
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
