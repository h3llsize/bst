<template>
  <div class="notice">
    <a href="tel:+79128872684">
      +7 912 887-26-84
    </a>
  </div>
</template>

<style lang='scss' scoped>
.notice {
  position: absolute;
  right: vw(40);
  top: 50%;
  transform: translateY(-50%);

  a {
    font-weight: 600;
    font-size: vw(24);

    @include sm {
      font-size: sm-vw(20);
    }
  }
}
</style>
