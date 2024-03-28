<script lang='ts' setup>
import FeedbackForm from './feedback-form.vue'

defineProps<{ active?: boolean }>()
const model = defineModel()

function handleClick(event: MouseEvent) {
  if ((event.target as HTMLDialogElement).className === 'feedback__modal modal active')
    model.value = false
}
</script>

<template>
  <dialog
    class="feedback__modal modal"
    :class="{ active }"
    @click="handleClick"
  >
    <FeedbackForm
      @submit.prevent="model = false"
      @click.stop="handleClick"
    />
  </dialog>
</template>

<style lang='scss' scoped>

</style>
