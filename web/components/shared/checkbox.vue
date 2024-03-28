<script lang='ts' setup>
defineProps<{ id: string }>()
const emit = defineEmits(['update:modelValue'])

function handleChange(event: Event) {
  emit('update:modelValue', (event.target as HTMLInputElement).checked)
}
</script>

<template>
  <label
    :for="id"
    class="label"
  >
    <input
      :id="id"
      class="input"
      type="checkbox"
      :name="id"
      @change="handleChange"
    >
    <span class="checkbox" />
    <p class="text noselect">
      <slot />
    </p>
  </label>
</template>

<style lang='scss' scoped>
.label {
  display: flex;
  align-items: center;
  gap: vw(6);

  cursor: pointer;

  @include xxl {
    gap: xxl-vw(6);
  }

  @include lg {
    gap: lg-vw(6);
  }

  @include sm {
    gap: sm-vw(6);
  }
}

.input {
  display: none;

  &:checked + .checkbox::after {
    opacity: 1;
  }
}

.checkbox {
  display: flex;
  align-items: center;
  justify-content: center;

  flex-shrink: 0;

  width: vw(18);
  height: vw(18);
  border: 2px solid #0e88f7;
  border-radius: 3px;

  @include xxl {
    width: xxl-vw(18);
    height: xxl-vw(18);
  }

  @include lg {
    width: lg-vw(18);
    height: lg-vw(18);
  }

  @include sm {
    width: sm-vw(18);
    height: sm-vw(18);
  }

  &::after {
    content: '';

    width: vw(8);
    height: vw(4);
    border-left: 2px solid #0e88f7;
    border-bottom: 2px solid #0e88f7;
    transform: rotate(-45deg);
    margin-bottom: 2px;

    opacity: 0;
    transition: opacity 0.25s ease;

    @include xxl {
      width: xxl-vw(8);
      height: xxl-vw(4);
    }

    @include lg {
      width: lg-vw(8);
      height: lg-vw(4);
    }

    @include sm {
      width: sm-vw(8);
      height: sm-vw(4);
    }
  }
}

.text {
  font-size: vw(20);

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}
</style>
