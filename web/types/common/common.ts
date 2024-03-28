export interface FilterOption {
  id: number
  name: string
  value: boolean
}

export interface FilterCategory {
  id: number
  name: string
  categoryOptions: FilterOption[]
}

export interface FilterDto {
  optionsId: number[]
}

export interface HoverData {
  state: boolean
  slug: string
}

export interface MetaTag {
  openGraph: OpenGraphMeta
  otherDTO: OtherMeta
}

export interface OpenGraphMeta {
  title: string | null
  description: string | null
  type: string | null
  image: string | null
  locale: string | null
  locality: string | null
  siteName: string | null
}

export interface OtherMeta {
  title: string | null
  description: string | null
  keywords: string | null
}
