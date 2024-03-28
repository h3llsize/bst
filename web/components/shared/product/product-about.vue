<script lang='ts' setup>
interface Props {
  description: string
  manufacturing: string
  advantages: string
  range: string
}

defineProps<Props>()
</script>

<template>
  <div class="advantages">
    <h3
      v-if="description"
      class="advantages__heading"
    >
      Описание
    </h3>
    <p
      v-if="description"
      class="advantages__desc"
    >
      {{ description }}
    </p>
    <h3
      v-if="manufacturing"
      class="advantages__heading"
    >
      Порядок изготовления
    </h3>
    <p
      v-if="manufacturing"
      class="advantages__desc"
    >
      {{ manufacturing }}
    </p>
    <h3
      v-if="advantages"
      class="advantages__heading"
    >
      Основные преимущества
    </h3>
    <p
      v-if="advantages"
      class="advantages__desc"
    >
      {{ advantages }}
    </p>
    <h3
      v-if="range"
      class="advantages__heading"
    >
      Область применения
    </h3>
    <p
      v-if="range"
      class="advantages__desc"
    >
      {{ range }}
    </p>
  </div>
</template>

<style lang='scss' scoped>
.advantages {
  border-top: vw(2) solid #0e88f7;
  padding-top: vw(50);

  @include xxl {
    border-top: xxl-vw(2) solid #0e88f7;
    padding-top: xxl-vw(50);
  }

  @include lg {
    border-top: lg-vw(2) solid #0e88f7;
    padding-top: lg-vw(50);
  }

  @include sm {
    border-top: sm-vw(2) solid #0e88f7;
    padding-top: sm-vw(50);
  }
}

.advantages__heading {
  font-weight: 500;
  font-size: vw(32);

  margin-bottom: vw(10);

  @include xxl {
    font-size: xxl-vw(32);

    margin-bottom: xxl-vw(10);
  }

  @include lg {
    font-size: lg-vw(32);

    margin-bottom: lg-vw(10);
  }

  @include sm {
    font-size: sm-vw(32);

    margin-bottom: sm-vw(20);
  }
}

.advantages__desc {
  font-size: vw(20);
  line-height: 200%;
  word-spacing: 0.5px;
  letter-spacing: 0.1px;

  margin-bottom: vw(30);

  @include xxl {
    font-size: xxl-vw(20);

    margin-bottom: xxl-vw(30);
  }

  @include lg {
    font-size: lg-vw(20);

    margin-bottom: lg-vw(30);
  }

  @include sm {
    font-size: sm-vw(20);

    margin-bottom: sm-vw(60);
  }
}
</style>
