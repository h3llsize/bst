<script lang='ts' setup>
import { FeedbackPostDTO, Services } from '#/utils/api'

const props = defineProps<{ hasCart?: boolean }>()

const store = useStore()

const form = ref<HTMLFormElement | null>(null)
const inputs = reactive({
  name: {
    type: 'input',
    name: 'Имя',
    inputName: 'name',
    placeholder: 'Как вас зовут',
    value: '',
  },
  tel: {
    type: 'input',
    name: 'Телефон',
    inputName: 'tel',
    placeholder: 'Ваш номер телефона',
    value: '',
  },
  email: {
    type: 'input',
    name: 'Email',
    inputName: 'email',
    placeholder: 'Ваша электронная почта',
    value: '',
  },
  comment: {
    type: 'textarea',
    name: 'Комментарий',
    inputName: 'comment',
    placeholder: 'Ваш комментарий к заявке',
    value: '',
  },
})

async function submit() {
  const dto = new FeedbackPostDTO()

  dto.name = inputs.name.value
  dto.tel = inputs.tel.value
  dto.email = inputs.email.value
  dto.comment = inputs.comment.value
  dto.cart = props.hasCart ? store.getCart : []

  try {
    await Services.postFeedback(dto)

    form.value?.reset?.()
  }
  catch (e) {

  }
}
</script>

<template>
  <form
    ref="form"
    action="#"
    class="feedback__form"
    autocomplete="off"
    @submit.prevent="submit"
  >
    <label
      v-for="input in Object.values(inputs)"
      :key="Object.values(inputs).indexOf(input)"
      :for="`feedback_input[${Object.values(inputs).indexOf(input)}]`"
      class="feedback__label"
      :class="{ comment: input.type === 'textarea' }"
    >
      <span class="feedback__name">
        {{ input.name }}:
      </span>
      <input
        v-if="input.type === 'input'"
        :id="`feedback_input[${Object.values(inputs).indexOf(input)}]`"
        v-model="input.value"
        type="text"
        :name="input.inputName"
        :placeholder="input.placeholder"
        required
        class="feedback__input"
      >
      <textarea
        v-else
        :id="`feedback_input[${Object.values(inputs).indexOf(input)}]`"
        v-model="input.value"
        :name="input.inputName"
        :placeholder="input.placeholder"
        maxlength="400"
        class="feedback__input"
      />
    </label>

    <button
      class="feedback__send"
      type="submit"
    >
      Оставить заявку
    </button>
  </form>
</template>

<style lang='scss' scoped>
.feedback__form {
  display: flex;
  flex-direction: column;
  gap: vw(30);

  padding: vw(30);
  border-radius: vw(20);
  background: #fff;
  box-shadow: 0 0 vw(30) 0 rgba(#000, 0.05);

  @include xxl {
    gap: xxl-vw(30);

    padding: xxl-vw(30);
    border-radius: xxl-vw(20);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#000, 0.05);
  }

  @include lg {
    gap: lg-vw(30);

    padding: lg-vw(30);
    border-radius: lg-vw(20);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#000, 0.05);
  }

  @include sm {
    gap: sm-vw(30);

    padding: sm-vw(30);
    border-radius: sm-vw(20);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#000, 0.05);
  }
}

.feedback__label {
  display: grid;
  grid-template-columns: auto vw(470);
  grid-gap: vw(10);

  @include xxl {
    grid-template-columns: auto xxl-vw(470);
    grid-gap: xxl-vw(10);
  }

  @include lg {
    grid-template-columns: 1fr 3fr;
    grid-gap: lg-vw(10);
  }

  @include sm {
    grid-gap: sm-vw(10);
  }
}

.feedback__name {
  display: flex;
  align-items: center;
  justify-content: flex-start;

  font-weight: 500;
  font-size: vw(26);

  @include xxl {
    font-size: xxl-vw(26);
  }

  @include lg {
    font-size: lg-vw(26);
  }

  @include sm {
    font-size: sm-vw(20);
  }
}

.feedback__input {
  width: 100%;
  padding: vw(15) vw(30);

  border-radius: vw(30);
  background: #fff;
  border: vw(2) solid #0e88f7;
  box-shadow: 0 0 vw(15) rgba(#0e88f7, 0.1);

  font-weight: 500;
  font-size: vw(24);

  @include xxl {
    padding: xxl-vw(15) xxl-vw(30);

    border-radius: xxl-vw(30);
    border: xxl-vw(2) solid #0e88f7;
    box-shadow: 0 0 xxl-vw(15) rgba(#0e88f7, 0.1);

    font-size: xxl-vw(24);
  }

  @include lg {
    padding: lg-vw(15) lg-vw(30);

    border-radius: lg-vw(30);
    border: lg-vw(2) solid #0e88f7;
    box-shadow: 0 0 lg-vw(15) rgba(#0e88f7, 0.1);

    font-size: lg-vw(24);
  }

  @include sm {
    padding: sm-vw(10) sm-vw(20);

    border-radius: sm-vw(30);
    border: sm-vw(2) solid #0e88f7;
    box-shadow: 0 0 sm-vw(15) rgba(#0e88f7, 0.1);

    font-size: sm-vw(20);
  }
}

.feedback__label.comment {
  display: flex;
  flex-direction: column;
  gap: vw(10);

  @include xxl {
    gap: xxl-vw(10);
  }

  @include lg {
    gap: lg-vw(10);
  }

  @include sm {
    gap: sm-vw(20);
  }

  .feedback__input {
    outline: none;
    min-height: vw(200);
    max-height: vw(400);
    resize: vertical;

    @include xxl {
      min-height: xxl-vw(200);
      max-height: xxl-vw(400);
    }

    @include lg {
      min-height: lg-vw(200);
      max-height: lg-vw(400);
    }

    @include sm {
      min-height: sm-vw(200);
      max-height: sm-vw(400);
    }
  }
}

.feedback__send {
  display: flex;
  align-items: center;
  justify-content: center;

  width: 100%;
  padding: vw(13) vw(50);

  background: #0e88f7;
  border-radius: vw(30);
  box-shadow: 0 0 vw(30) 0 rgba(#0e88f7, 0.25);

  font-weight: 600;
  font-size: vw(32);
  color: #fff;

  @include xxl {
    padding: xxl-vw(13) xxl-vw(50);

    border-radius: xxl-vw(30);
    box-shadow: 0 0 xxl-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: xxl-vw(32);
  }

  @include lg {
    padding: lg-vw(13) lg-vw(50);

    border-radius: lg-vw(30);
    box-shadow: 0 0 lg-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: lg-vw(32);
  }

  @include sm {
    padding: sm-vw(13) sm-vw(50);

    border-radius: sm-vw(30);
    box-shadow: 0 0 sm-vw(30) 0 rgba(#0e88f7, 0.25);

    font-size: sm-vw(32);
  }
}
</style>
