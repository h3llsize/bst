<script lang='ts' setup>
import { RouteNames } from '#/types/routes'
import ListCategories from '#/components/pages/category/list-categories.vue'
import { Services } from '#/utils/api'
import type { Category } from '#/types/models'

const route = useRoute()
const router = useRouter()

const slug = Array.isArray(route.params.id) ? route.params.id[0] : route.params.id

const category = ref<Category>({
  id: 0,
  name: '',
  slug: '',
  catalogSubCategories: [],
  imagePath: '',
})

async function loadCategory() {
  try {
    const data = await Services.getCategory(slug)
    category.value = data

    useSeoMeta({
      title: `${category.value.name}`,
    })
  }
  catch (e) {
    router.push({ name: RouteNames.Error })
  }
}

void (await useLoadMeta({ slug, type: RouteNames.Category })).applySeoMeta()

await loadCategory()

definePageMeta({
  hasDropdown: true,
})
</script>

<template>
  <section class="category">
    <div class="container">
      <h2 class="category__heading">
        {{ category.name }}
      </h2>
      <ListCategories
        :items="category.catalogSubCategories"
        type="subcategory"
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
