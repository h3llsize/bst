<script lang='ts' setup>
</script>

<template>
  <section class="error">
    <div class="container">
      <h2 class="error__heading">
        404. Старница не найдена :с
      </h2>
    </div>
  </section>
</template>

<style lang='scss' scoped>
.error__heading {
  font-weight: 700;
  font-size: vw(60);

  text-align: center;
  margin-top: vw(50);

  @include xxl {
    font-size: xxl-vw(50);

    margin-top: xxl-vw(50);
  }

  @include lg {
    font-size: lg-vw(50);

    margin-top: lg-vw(50);
  }
}
</style>
