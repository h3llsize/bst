<script lang='ts' setup>
</script>

<template>
  <div class="arrow">
    <span />
  </div>
</template>

<style lang='scss' scoped>
.arrow {
  display: flex;
  align-items: center;
  justify-content: center;

  width: vw(50);
  height: vw(50);
  border-radius: 100%;
  background: #0e88f7;
  box-shadow: 0 0 vw(20) 0 rgba(#0e88f7, 0.25);

  @include xxl {
    width: xxl-vw(50);
    height: xxl-vw(50);
    box-shadow: 0 0 xxl-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include lg {
    width: lg-vw(50);
    height: lg-vw(50);
    box-shadow: 0 0 lg-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include md {
    width: md-vw(50);
    height: md-vw(50);
    box-shadow: 0 0 md-vw(20) 0 rgba(#0e88f7, 0.25);
  }

  @include sm {
    width: sm-vw(50);
    height: sm-vw(50);
    box-shadow: 0 0 sm-vw(20) 0 rgba(#0e88f7, 0.25);
  }
}

.arrow span {
  width: vw(18);
  height: vw(18);
  border-left: vw(2) solid #fff;
  border-bottom: vw(2) solid #fff;
  transform: rotate(-45deg) translate(15%, -15%);

  @include xxl {
    width: xxl-vw(18);
    height: xxl-vw(18);
    border-left: xxl-vw(2) solid #fff;
    border-bottom: xxl-vw(2) solid #fff;
  }

  @include lg {
    width: lg-vw(18);
    height: lg-vw(18);
    border-left: lg-vw(2) solid #fff;
    border-bottom: lg-vw(2) solid #fff;
  }

  @include md {
    width: md-vw(18);
    height: md-vw(18);
    border-left: md-vw(2) solid #fff;
    border-bottom: md-vw(2) solid #fff;
  }

  @include sm {
    width: sm-vw(18);
    height: sm-vw(18);
    border-left: sm-vw(2) solid #fff;
    border-bottom: sm-vw(2) solid #fff;
  }
}
</style>
