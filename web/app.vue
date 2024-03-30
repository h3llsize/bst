<script lang="ts" setup>
const app = useAppConfig()

useHead({
  titleTemplate: '%s',
  ...app,
})
</script>

<template>
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
