<script lang='ts' setup>
import type { Option } from '#/types/models'

defineProps<{ options: Option[] }>()
</script>

<template>
  <ul class="product__options">
    <li
      v-for="option in options"
      :key="option.name"
      class="product__option"
    >
      <h4 class="product__name-option">
        {{ option.name }}:
      </h4>
      <span class="product__param-option">
        {{ option.param }}
      </span>
    </li>
  </ul>
</template>

<style lang='scss' scoped>
.product__options {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: vw(20);

  width: 100%;

  @include xxl {
    grid-gap: xxl-vw(20);
  }

  @include lg {
    grid-template-columns: repeat(3, 1fr);
    grid-gap: lg-vw(20);
  }

  @include md {
    grid-template-columns: repeat(2, 1fr);
    grid-gap: md-vw(20);
  }
}

.product__option {
  display: inline-flex;
  align-items: center;
  justify-content: flex-start;
  gap: vw(5);

  padding: vw(12) vw(25);
  border-radius: vw(10);
  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.08);

  @include xxl {
    gap: xxl-vw(5);

    padding: xxl-vw(12) xxl-vw(25);
    border-radius: xxl-vw(10);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.08);
  }

  @include lg {
    gap: lg-vw(5);

    padding: lg-vw(12) lg-vw(25);
    border-radius: lg-vw(10);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.08);
  }

  @include md {
    gap: md-vw(5);

    padding: md-vw(12) md-vw(25);
    border-radius: md-vw(10);
    box-shadow: 0 0 md-vw(30) 0 rgba(#000, 0.08);
  }

  @include sm {
    gap: sm-vw(5);

    padding: sm-vw(12) sm-vw(25);
    border-radius: sm-vw(10);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#000, 0.08);
  }
}

.product__name-option {
  font-weight: 600;
  font-size: vw(16);

  @include xxl {
    font-size: xxl-vw(16);
  }

  @include lg {
    font-size: lg-vw(16);
  }

  @include md {
    font-size: md-vw(16);
  }

  @include sm {
    font-size: sm-vw(16);
  }
}

.product__param-option {
  font-size: vw(16);

  @include xxl {
    font-size: xxl-vw(16);
  }

  @include lg {
    font-size: lg-vw(16);
  }

  @include md {
    font-size: md-vw(16);
  }

  @include sm {
    font-size: sm-vw(16);
  }
}
</style>
