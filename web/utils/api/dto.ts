import type { FilterDto } from '#/types/common'
import type { RouteNames } from '#/types/routes'
import type { CartProduct } from '#/types/models'

export interface Dto {
  serialize: <T>() => Record<string, T>
}

export class FeedbackPostDTO implements Dto {
  name: string | null = null
  tel: string | null = null
  email: string | null = null
  comment: string | null = null
  cart: CartProduct[] = []

  serialize<T>(): Record<string, T> {
    const dto: Record<string, T> = Object.assign(this)

    Object.keys(dto).forEach((key) => {
      if (dto[key] === undefined)
        delete dto[key]
    })

    return dto
  }
}

export class CatalogPostDTO implements Dto {
  page: number | null = null
  categoryId: number | null = null
  query: string | null = null
  filters: FilterDto[] | null = null

  serialize<T>(): Record<string, T> {
    const dto: Record<string, T> = Object.assign(this)

    Object.keys(dto).forEach((key) => {
      if (dto[key] === undefined)
        delete dto[key]
    })

    return dto
  }
}

export class MetaPostDTO implements Dto {
  type: RouteNames | string | null = null
  slug: string | null = null
  city: string | null = null

  serialize<T>(): Record<string, T> {
    const dto: Record<string, T> = Object.assign(this)

    Object.keys(dto).forEach((key) => {
      if (dto[key] === undefined)
        delete dto[key]
    })

    return dto
  }
}
