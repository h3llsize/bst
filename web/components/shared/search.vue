<script lang='ts' setup>
const emit = defineEmits<{
  search: [value: string]
}>()

const search = ref<string>('')

function handleSubmit() {
  emit('search', search.value)
}
</script>

<template>
  <form
    action="#"
    autocomplete="off"
    class="form"
    @submit.prevent="handleSubmit"
  >
    <input
      v-model="search"
      type="text"
      class="input"
      placeholder="Поиск по каталогу"
    >

    <button
      class="button noselect"
      type="submit"
    >
      Найти
    </button>
  </form>
</template>

<style lang='scss' scoped>
.form {
  display: flex;
  align-items: center;
  gap: vw(20);

  width: 100%;

  @include xxl {
    gap: xxl-vw(20);
  }

  @include lg {
    gap: lg-vw(20);
  }

  @include md {
    gap: md-vw(20);
  }

  @include sm {
    gap: sm-vw(20);
  }
}

.input {
  width: 100%;
  padding: vw(19) vw(30);
  border-radius: vw(25);
  background: #fff;
  box-shadow: 0 0 vw(20) 0 rgba(#000, 0.05);

  font-size: vw(26);

  @include xxl {
    padding: xxl-vw(19) xxl-vw(30);
    border-radius: xxl-vw(25);
    box-shadow: 0 0 xxl-vw(20) 0 rgba(#000, 0.05);

    font-size: xxl-vw(26);
  }
  @include lg {
    padding: lg-vw(19) lg-vw(30);
    border-radius: lg-vw(25);
    box-shadow: 0 0 lg-vw(20) 0 rgba(#000, 0.05);

    font-size: lg-vw(26);
  }

  @include md {
    padding: md-vw(19) md-vw(30);
    border-radius: md-vw(25);
    box-shadow: 0 0 md-vw(20) 0 rgba(#000, 0.05);

    font-size: md-vw(26);
  }

  @include sm {
    padding: sm-vw(19) sm-vw(30);
    border-radius: sm-vw(25);
    box-shadow: 0 0 sm-vw(20) 0 rgba(#000, 0.05);

    font-size: sm-vw(26);
  }
}

.button {
  flex-shrink: 0;

  padding: vw(19) vw(56);
  border-radius: vw(25);
  background: #0e88f7;
  box-shadow: 0 0 vw(30) 0 rgba(#0e88f7, 0.25);

  font-weight: 600;
  font-size: vw(26);
  color: #fff;

  @include xxl {
    padding: xxl-vw(19) xxl-vw(56);
    border-radius: xxl-vw(25);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: xxl-vw(26);
  }

  @include lg {
    padding: lg-vw(19) lg-vw(56);
    border-radius: lg-vw(25);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: lg-vw(26);
  }

  @include md {
    padding: md-vw(19) md-vw(56);
    border-radius: md-vw(25);
    box-shadow: 0 0 md-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: md-vw(26);
  }

  @include sm {
    padding: sm-vw(19) sm-vw(56);
    border-radius: sm-vw(25);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: sm-vw(26);
  }
}
</style>
