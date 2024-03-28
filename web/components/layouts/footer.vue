<script lang='ts' setup>
import Logo from '#/components/shared/logo.vue'
import Nav from '#/components/shared/nav.vue'
import PhoneNotice from '#/components/shared/phone-notice.vue'
import SelectCity from '#/components/shared/city/select-city.vue'
</script>

<template>
  <footer class="footer">
    <div class="container">
      <Logo />
      <SelectCity class="footer__city" />
      <Nav
        direction="row"
        class="footer__nav"
      />
    </div>
    <PhoneNotice class="phone-notice" />
  </footer>
</template>

<style lang='scss' scoped>
.footer {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 10;

  min-height: vw(75);

  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000000, 0.05);

  @include xxl {
    min-height: xxl-vw(75);

    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000000, 0.05);
  }

  @include lg {
    min-height: lg-vw(75);

    box-shadow: 0 0 lg-vw(30) 0 rgba(#000000, 0.05);
  }

  @include sm {
    min-height: sm-vw(75);

    box-shadow: 0 0 sm-vw(30) 0 rgba(#000000, 0.05);
  }
}

.container {
  display: flex;
  align-items: center;
  justify-content: space-between;

  height: vw(75);

  @include xxl {
    height: xxl-vw(75);
  }

  @include lg {
    height: lg-vw(75);
  }

  @include sm {
    height: sm-vw(75);
  }
}

.footer__city {
  margin-left: vw(20);
}

.phone-notice {
  @include xxl {
    display: none;
  }
}

.footer__nav {
  @include sm {
    display: none;
  }
}
</style>
