import type { Product } from '#/types/models/product'

export interface MainCategory {
  id: number
  name: string
  slug: string
  categories: Category[]
}

export interface Category {
  id: number
  name: string
  slug: string
  imagePath: string
  products: Product[]
  catalogSubCategories: SubCategory[]
}

export interface SubCategory {
  id: number
  name: string
  slug: string
  imagePath: string
  products: Product[]
}

export interface CatalogSubCategory {
  id: number
  slug: string
  name: string
}
