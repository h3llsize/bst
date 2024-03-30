<script lang='ts' setup>
import Feedback from '#/components/shared/feedback/feedback.vue'
import { RouteNames } from '#/types/routes'

void (await useLoadMeta({ type: RouteNames.Feedback })).applySeoMeta()
</script>

<template>
  <section class="contacts">
    <div class="container">
      <Feedback
        :has-cart="false"
        class="contacts__feedback"
      >
        Обратная связь
      </Feedback>
    </div>
  </section>
</template>
