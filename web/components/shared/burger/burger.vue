<template>
  <button class="burger">
    <span />
    <span />
    <span />
  </button>
</template>

<style lang='scss' scoped>
.burger {
  display: flex;
  flex-direction: column;
  gap: vw(7);

  width: vw(35);

  @include xxl {
    gap: xxl-vw(7);

    width: xxl-vw(35);
  }

  @include sm {
    justify-content: space-between;
    gap: 0;

    width: sm-vw(40);
    height: sm-vw(35);
  }

  & span {
    width: 100%;
    height: vw(5);

    background: #062120;
    border-radius: vw(10);

    @include xxl {
      height: xxl-vw(5);

      border-radius: xxl-vw(10);
    }

    @include sm {
      height: sm-vw(5);
    }
  }
}
</style>
