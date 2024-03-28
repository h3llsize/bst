<script lang='ts' setup>
</script>

<template>
  <div class="loader" />
</template>

<style lang='scss' scoped>
.loader {
  width: vw(100);
  height: vw(100);
  border: vw(10) solid #fff;
  border-bottom-color: #0e88f7;
  border-radius: 50%;
  display: inline-block;
  box-sizing: border-box;
  animation: rotation 1s linear infinite;

  @include xxl {
    width: xxl-vw(100);
    height: xxl-vw(100);
    border: xxl-vw(10) solid #fff;
    border-bottom-color: #0e88f7;
  }

  @include lg {
    width: lg-vw(100);
    height: lg-vw(100);
    border: lg-vw(10) solid #fff;
    border-bottom-color: #0e88f7;
  }

  @include md {
    width: md-vw(100);
    height: md-vw(100);
    border: md-vw(10) solid #fff;
    border-bottom-color: #0e88f7;
  }

  @include sm {
    width: sm-vw(100);
    height: sm-vw(100);
    border: sm-vw(10) solid #fff;
    border-bottom-color: #0e88f7;
  }
}

@keyframes rotation {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
