<script lang='ts' setup>
import ListCategories from '#/components/pages/category/list-categories.vue'
import { Services } from '#/utils/api'
import { RouteNames } from '#/types/routes'
import type { Category, SubCategory } from '#/types/models'

const route = useRoute()
const router = useRouter()

const slug = Array.isArray(route.params.id) ? route.params.id[0] : route.params.id

const categories = ref<Category[]>([])

const newCategories = computed<SubCategory[]>(() => {
  // eslint-disable-next-line unused-imports/no-unused-vars
  return categories.value.map(({ catalogSubCategories, ...rest }) => rest)
})
async function loadCategories() {
  try {
    categories.value = await Services.getCategories(slug)
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

void (await useLoadMeta({ slug, type: RouteNames.Maincategory })).applySeoMeta()

await loadCategories()

definePageMeta({
  hasDropdown: true,
})
</script>

<template>
  <section class="main-category">
    <div class="container">
      <h2 class="category__heading">
        Главная категория
      </h2>

      <ListCategories
        :items="newCategories"
        type="category"
      />
    </div>
  </section>
</template>

<style lang='scss' scoped>
.category__heading {
  font-weight: 600;
  font-size: vw(42);

  margin-bottom: vw(30);

  @include xxl {
    font-size: xxl-vw(42);

    margin-bottom: xxl-vw(30);
  }

  @include lg {
    font-size: lg-vw(42);

    margin-bottom: lg-vw(30);
  }
}
</style>
