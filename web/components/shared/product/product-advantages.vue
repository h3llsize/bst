<script lang="ts" setup>
import TruckIcon from '#/assets/svg/truck.svg?url'
import SawIcon from '#/assets/svg/saw.svg?url'
import MoneyIcon from '#/assets/svg/money.svg?url'
</script>

<template>
  <div class="box">
    <h4 class="heading">
      Наши преимущества
    </h4>
    <ul class="list">
      <li class="item">
        <img
          class="img"
          :src="TruckIcon"
          alt="truck"
        >
        <span>
          Быстрая доставка<br>до адреса
        </span>
      </li>
      <li class="item">
        <img
          class="img"
          :src="SawIcon"
          alt="saw"
        >
        <span>
          Резка в размер<br>по чертежам
        </span>
      </li>
      <li class="item">
        <img
          class="img"
          :src="MoneyIcon"
          alt="money"
        >
        <span>
          Отслеживание по<br>трек-номеру
        </span>
      </li>
    </ul>
  </div>
</template>

<style lang='scss' scoped>
.box {
  padding: vw(40) vw(20);
  border-radius: vw(20);
  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.05);

  @include xxl {
    padding: xxl-vw(40) xxl-vw(20);
    border-radius: xxl-vw(20);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.05);
  }

  @include lg {
    padding: lg-vw(40) lg-vw(20);
    border-radius: lg-vw(20);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.05);
  }

  @include sm {
    padding: sm-vw(40) sm-vw(20);
    border-radius: sm-vw(20);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#000, 0.05);
  }
}

.list {
  display: grid;
  grid-template-rows: repeat(3, 1fr);
  grid-gap: vw(60);

  @include xxl {
    grid-gap: xxl-vw(60);
  }

  @include lg {
    grid-gap: lg-vw(60);
  }

  @include sm {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: sm-vw(60);
  }
}

.heading {
  font-weight: 700;
  font-size: vw(24);
  line-height: 100%;

  text-align: center;
  margin-bottom: vw(40);

  @include xxl {
    font-size: xxl-vw(24);

    margin-bottom: xxl-vw(40);
  }

  @include lg {
    font-size: lg-vw(24);

    margin-bottom: lg-vw(40);
  }

  @include sm {
    font-size: sm-vw(24);

    margin-bottom: sm-vw(40);
  }
}

.img {
  width: vw(40);
  height: vw(40);

  @include xxl {
    width: xxl-vw(40);
    height: xxl-vw(40);
  }

  @include lg {
    width: lg-vw(40);
    height: lg-vw(40);
  }

  @include sm {
    width: sm-vw(40);
    height: sm-vw(40);
  }
}

.item {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: vw(20);

  padding-left: vw(20);
  border-radius: vw(10);

  font-weight: 600;
  font-size: vw(20);

  @include xxl {
    gap: xxl-vw(20);

    padding-left: xxl-vw(20);
    border-radius: xxl-vw(10);

    font-size: xxl-vw(20);
  }

  @include lg {
    gap: lg-vw(20);

    padding-left: lg-vw(20);
    border-radius: lg-vw(10);

    font-size: lg-vw(20);
  }

  @include sm {
    gap: sm-vw(20);

    padding-left: 0;
    border-radius: sm-vw(10);

    font-size: sm-vw(20);
  }
}
</style>
