<script lang="ts" setup>
const app = useAppConfig()

useHead({
  title: app.name,
  htmlAttrs: { lang: 'ru' },
  titleTemplate: '%s',
  meta: [{
    name: 'viewport',
    content:
        'viewport-fit=cover, initial-scale=1, maximum-scale=1, user-scalable=no',
  }],
  link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
})
</script>

<template>
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
