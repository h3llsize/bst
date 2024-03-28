<script lang='ts' setup>
import { RouteNames } from "~/types/routes";

const contacts = [{
  name: 'Телефон',
  value: '+7 (912) 887-26-84',
}, {
  name: 'Почта',
  value: 'zakaz@bst-sl.ru',
}, {
  name: 'Юридический адрес',
  value: 'Пермский край, м. о. Пермский, п. Кукуштан, ул. Королева, д. 7',
}]

const workTime = [{
  name: 'Пн - Пт',
  value: '9.00 - 18.00',
}, {
  name: 'Сб - Вс',
  value: 'Выходной',
}]

void (await useLoadMeta({ type: RouteNames.Contacts })).applySeoMeta()
</script>

<template>
  <section class="contacts">
    <div class="container">
      <h2 class="contacts__heading">
        Контакты
      </h2>

      <ul class="contacts__list">
        <li
          v-for="item in contacts"
          :key="item.name"
          class="contacts__item"
        >
          <span class="contacts__name">
            {{ item.name }}:
          </span>
          <span class="contacts__value">
            {{ item.value }}
          </span>
        </li>
      </ul>

      <h2 class="contacts__heading">
        График работы
      </h2>

      <ul class="contacts__list">
        <li
          v-for="item in workTime"
          :key="item.name"
          class="contacts__item"
        >
          <span class="contacts__name">
            {{ item.name }}:
          </span>
          <span class="contacts__value">
            {{ item.value }}
          </span>
        </li>
      </ul>
    </div>
  </section>
</template>

<style lang='scss' scoped>
.contacts__heading {
  font-weight: 600;
  font-size: vw(42);

  margin-bottom: vw(40);

  @include xxl {
    font-size: xxl-vw(42);

    margin-bottom: xxl-vw(40);
  }

  @include lg {
    font-size: lg-vw(42);

    margin-bottom: lg-vw(40);
  }

  @include sm {
    font-size: sm-vw(42);

    margin-bottom: sm-vw(40);
  }

  &:not(:first-child) {
    margin-top: vw(50);

    @include xxl {
      margin-top: xxl-vw(50);
    }

    @include lg {
      margin-top: lg-vw(50);
    }

    @include sm {
      margin-top: sm-vw(50);
    }
  }
}

.contacts__list {
  display: flex;
  flex-direction: column;
  gap: vw(20);

  @include xxl {
    gap: xxl-vw(20);
  }

  @include lg {
    gap: lg-vw(20);
  }

  @include sm {
    gap: lg-vw(20);
  }
}

.contacts__name {
  font-weight: 500;
  font-size: vw(26);

  @include xxl {
    font-size: xxl-vw(26);
  }

  @include lg {
    font-size: lg-vw(26);
  }

  @include sm {
    font-size: sm-vw(26);
  }
}

.contacts__value {
  max-width: 70%;
  font-size: vw(26);
  color: #0e88f7;

  @include xxl {
    font-size: xxl-vw(26);
  }

  @include lg {
    font-size: lg-vw(26);
  }

  @include sm {
    font-size: sm-vw(26);
  }
}
</style>
