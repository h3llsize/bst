export interface CartProduct {
  id: number
  amount: number

  slug?: string
  name?: string
  price?: number
  full?: boolean
  hasBtn?: boolean
}

export interface Product {
  id: number
  categoryId: number
  slug: string
  name: string
  price: number
  options: Option[]
  pathToImage: string | null
  description: string | null
  manufacturing: string | null
  advantages: string | null
  range: string | null
}

export interface Option {
  name: string
  param: string
}
