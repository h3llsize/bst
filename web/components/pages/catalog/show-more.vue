<script lang='ts' setup>
import Arrow from '#/components/shared/arrow.vue'

defineProps<{ count: number }>()
</script>

<template>
  <button class="show-more">
    <Arrow />
    <span class="show-more__text">
      показать еще ({{ count }})
    </span>
  </button>
</template>

<style lang='scss' scoped>
.show-more {
  display: flex;
  align-items: center;
  gap: vw(10);

  @include xxl {
    gap: xxl-vw(10);
  }

  @include lg {
    gap: lg-vw(10);
  }

  @include md {
    gap: md-vw(10);
  }

  @include sm {
    gap: sm-vw(10);
  }
}

.show-more__text {
  font-size: vw(18);

  @include xxl {
    font-size: xxl-vw(18);
  }

  @include lg {
    font-size: lg-vw(18);
  }

  @include md {
    font-size: md-vw(18);
  }

  @include sm {
    font-size: sm-vw(18);
  }
}
</style>
