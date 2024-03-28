<script  lang='ts' setup>
import Checkbox from '#/components/shared/checkbox.vue'
import type { FilterCategory } from '#/types/common'

defineProps<{ categories: FilterCategory[] }>()
const emit = defineEmits<{ submit: [] }>()

function handleClick() {
  emit('submit')
}
</script>

<template>
  <div class="filter">
    <ul
      v-for="category in categories"
      :key="category.name"
      class="filter__category"
    >
      <h3 class="filter__heading">
        {{ category.name }}
      </h3>
      <li
        v-for="option in category.categoryOptions"
        :key="option.name"
        class="filter__item"
      >
        <Checkbox
          :id="`filter_${categories.indexOf(category)}_${category.categoryOptions.indexOf(option)}`"
          v-model="option.value"
          @update:model-value="handleClick"
        >
          {{ option.name }}
        </Checkbox>
      </li>
    </ul>
  </div>
</template>

<style lang='scss' scoped>
.filter {
  display: flex;
  flex-direction: column;
  gap: vw(30);

  padding: vw(25);
  border-radius: vw(25);
  background: #fff;
  box-shadow: 0 0 vw(40) rgba(#000, 0.05);

  @include xxl {
    gap: xxl-vw(30);

    padding: xxl-vw(25);
    border-radius: xxl-vw(25);
    box-shadow: 0 0 xxl-vw(40) rgba(#000, 0.05);
  }

  @include lg {
    gap: lg-vw(30);

    padding: lg-vw(25);
    border-radius: lg-vw(25);
    box-shadow: 0 0 lg-vw(40) rgba(#000, 0.05);
  }

  @include sm {
    gap: sm-vw(30);

    padding: sm-vw(25);
    border-radius: sm-vw(25);
    box-shadow: 0 0 sm-vw(40) rgba(#000, 0.05);
  }
}

.filter__heading {
  font-weight: 600;
  font-size: vw(24);

  @include xxl {
    font-size: xxl-vw(24);
  }

  @include lg {
    font-size: lg-vw(24);
  }

  @include sm {
    font-size: sm-vw(24);
  }
}

.filter__category {
  display: flex;
  flex-direction: column;
  gap: vw(15);

  @include xxl {
    gap: xxl-vw(15);
  }

  @include lg {
    gap: lg-vw(15);
  }

  @include sm {
    gap: sm-vw(15);
  }
}
</style>
