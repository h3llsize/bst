<script lang='ts' setup>
import Arrow from './arrow.vue'

interface Props {
  pages: number
  active: number
  click: (page: number) => void
}

const props = defineProps<Props>()

function navClick(direction: number) {
  if (
    props.active + direction <= 0
    || props.active + direction > props.pages
  )
    return

  props.click(props.active + direction)
}

function pageClick(page: number) {
  if (page !== props.active)
    props.click(page)
}

const pagination = computed<number[]>(() => {
  const range = Array.from({ length: props.pages }, (_, i) => 1 + i)

  return range.slice(props.active - 2 < 0
    ? 0
    : props.active - 2, props.active - 2 < 0 ? 5 : props.active + 3)
})
</script>

<template>
  <div class="pagination">
    <Arrow
      class="pagination__navigate pagination__navigate-prev"
      :class="{ disabled: active - 1 <= 0 }"
      @click="navClick(-1)"
    />
    <ul class="pagination__list">
      <li
        v-for="page in pagination"
        :key="page"
        class="pagination__page"
        style="cursor: pointer"
        :class="{ active: page === active }"
        @click="pageClick(page)"
      >
        {{ page }}
      </li>
    </ul>

    <Arrow
      class="pagination__navigate pagination__navigate-next"
      :class="{ disabled: active + 1 > pages }"
      @click="navClick(+1)"
    />
  </div>
</template>

<style lang='scss' scoped>
.pagination {
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

.pagination__navigate {
  border-radius: vw(10);
  cursor: pointer;

  @include xxl {
    border-radius: xxl-vw(10);
  }

  @include lg {
    border-radius: lg-vw(10);
  }

  @include md {
    border-radius: md-vw(10);
  }

  @include sm {
    border-radius: sm-vw(10);
  }

  &.disabled {
    cursor: not-allowed;
    opacity: 0.7;
  }
}

.pagination__navigate-prev {
  transform: scale(0.8) rotate(90deg);
}

.pagination__navigate-next {
  transform: scale(0.8) rotate(-90deg);
}

.pagination__list {
  display: flex;
  align-items: center;

  height: vw(43);
  padding: 0 vw(20);

  border-radius: vw(10);
  background: #fff;
  box-shadow: 0 0 vw(20) 0 rgba(#000, 0.05);

  @include xxl {
    height: xxl-vw(43);
    padding: 0 xxl-vw(20);

    border-radius: xxl-vw(10);
    box-shadow: 0 0 xxl-vw(20) 0 rgba(#000, 0.05);
  }

  @include lg {
    height: lg-vw(43);
    padding: 0 lg-vw(20);

    border-radius: lg-vw(10);
    box-shadow: 0 0 lg-vw(20) 0 rgba(#000, 0.05);
  }

  @include md {
    height: md-vw(43);
    padding: 0 md-vw(20);

    border-radius: md-vw(10);
    box-shadow: 0 0 md-vw(20) 0 rgba(#000, 0.05);
  }

  @include sm {
    height: sm-vw(43);
    padding: 0 sm-vw(20);

    border-radius: sm-vw(10);
    box-shadow: 0 0 sm-vw(20) 0 rgba(#000, 0.05);
  }
}

.pagination__page {
  display: flex;
  align-items: center;
  justify-content: center;

  width: vw(43);
  height: 100%;

  font-weight: 500;
  font-size: vw(18);

  @include xxl {
    width: xxl-vw(43);

    font-size: xxl-vw(18);
  }

  @include lg {
    width: lg-vw(43);

    font-size: lg-vw(18);
  }

  @include md {
    width: md-vw(43);

    font-size: md-vw(18);
  }

  @include sm {
    width: sm-vw(43);

    font-size: sm-vw(18);
  }

  &.active {
    color: #0e88f7;
  }
}
</style>
