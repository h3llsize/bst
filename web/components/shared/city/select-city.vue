<script lang="ts" setup>
import CityModal from './city-modal.vue'
import { Services } from '#/utils/api'

const modal = ref<boolean>(false)
const subdomain = useCookie('subdomain').value ?? useStore().subdomain

const cities = await Services.getCities()

const getCityName = computed<string>(() => {
  const city = cities.filter(
    city => city.domain === useCookie('subdomain').value,
  )
  if (city?.length > 0)
    return city[0].name
  return ''
})
</script>

<template>
  <div class="select-city">
    <p class="select-city__text">
      Город:
    </p>
    <button
      class="select-city__btn"
      @click.prevent="modal = true"
    >
      {{ getCityName || 'Москва' }}
      <span class="select-city__arrow" />
    </button>
    <div v-if="cities?.length">
      <CityModal
        v-model="modal"
        :cities="cities"
        :subdomain="subdomain"
      />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.select-city {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: vw(10);

  @include xxl {
    gap: xxl-vw(10);
  }

  @include lg {
    gap: lg-vw(10);
  }

  @include md {
    gap: md-vw(10);
  }

  @include sm {
    gap: sm-vw(10);
  }
}

.select-city__text {
  width: auto;

  font-weight: 500;
  font-size: vw(20);

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }

  @include md {
    font-size: md-vw(20);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}

.select-city__btn {
  display: flex;
  align-items: center;
  gap: vw(5);

  font-weight: 500;
  font-size: vw(20);

  color: #0e88f7;

  @include xxl {
    font-size: xxl-vw(20);
  }

  @include lg {
    font-size: lg-vw(20);
  }

  @include md {
    font-size: md-vw(20);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}

.select-city__arrow {
  display: block;

  width: vw(10);
  height: vw(10);
  border-top: vw(2) solid #0e88f7;
  border-left: vw(2) solid #0e88f7;

  transform: rotate(-135deg);
  margin-bottom: vw(3);
}
</style>
