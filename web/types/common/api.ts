export interface APIResponse<T> {
  data: T
  status?: number
}

export interface PageableResponse<T> {
  content: T
  totalPages: number
  number: number
  totalElements: number
  size: number
}
