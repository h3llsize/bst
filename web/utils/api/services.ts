import { api } from './api'
import type { Dto } from './dto'
import type { APIResponse, FilterCategory, MetaTag, PageableResponse } from '#/types/common'
import type { Category, City, MainCategory, Product, SubCategory } from '#/types/models'

class API {
  public static async getMainCategories() {
    return api().get<APIResponse<MainCategory[]>>('category/main-categories').then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getCategories(slug: string) {
    return api().get<APIResponse<Category[]>>(`category/main-categories/get?slug=${slug}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getCategory(slug: string) {
    return api().get<APIResponse<Category[]>>(`category/categories?slug=${slug}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getSubCategories(activePage: number, query: string) {
    return api().get<APIResponse<PageableResponse<SubCategory[]>>>(`category/subcategory/search?page=${activePage - 1}&query=${query}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getSubCategory(slug: string) {
    return api().get<APIResponse<SubCategory>>(`category/subcategory/get?slug=${slug}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getProducts(dto: Dto) {
    return api().post<APIResponse<PageableResponse<Product[]>>>('category/products-list', { body: { ...dto.serialize() } }).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getProduct(id: number) {
    return api().get<APIResponse<Product>>(`category/product/get?id=${id}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getProductBySlug(slug: string) {
    return api().get<APIResponse<Product>>(`category/product?slug=${slug}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getFilters(id: number) {
    return api().get<APIResponse<FilterCategory[]>>(`category/filters?categoryId=${id}`).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async postFeedback(dto: Dto) {
    return api().post<APIResponse<PageableResponse<string>>>('category/request', { body: { ...dto.serialize() } }).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getMeta(dto: Dto) {
    return api().post<APIResponse<MetaTag>>('meta/get', { body: { ...dto.serialize() } }).then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getCities() {
    return api().get<Promise<APIResponse<City[]>>>('city/all').then((res) => {
      return { data: res.data.value }
    })
  }

  public static async getStartData(activePage: number, query: string) {
    return api().get<APIResponse<PageableResponse<SubCategory[]>>>(`category/subcategory/start?page=${activePage - 1}&query=${query}`).then((res) => {
      return { data: res.data.value }
    })
  }
}

export class Services {
  public static async getMainCategories(): Promise<MainCategory[]> {
    return await API.getMainCategories()
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data): MainCategory[] => {
        return data
      })
  }

  public static getStartData(activePage: number, query: string): Promise<PageableResponse<SubCategory[]>> {
    return API.getStartData(activePage, query)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data): PageableResponse<SubCategory[]> => {
        return data
      })
  }

  public static async getCategories(slug: string): Promise<Category[]> {
    return API.getCategories(slug)
      .then((response: APIResponse<any>) => {
        return response?.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data): Category[] => {
        return data
      })
  }

  public static async getCategory(slug: string): Promise<Category> {
    return await API.getCategory(slug)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data): Category => {
        return data
      })
  }

  public static async getSubCategories(activePage: number, query: string) {
    return await API.getSubCategories(activePage, query)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: PageableResponse<SubCategory[]>) => {
        return {
          content: data.content,
          activePage: data.number + 1,
          totalPages: data.totalPages,
        }
      })
  }

  public static async getSubCategory(slug: string) {
    return await API.getSubCategory(slug)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: SubCategory) => {
        return data
      })
  }

  public static async getProducts(dto: Dto) {
    return await API.getProducts(dto)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: PageableResponse<Product[]>) => {
        return {
          content: data.content,
          page: data.number + 1,
          pageSize: data.size,
          totalPages: data.totalPages,
          count: data.totalElements,
        }
      })
  }

  public static async getProduct(id: number) {
    return await API.getProduct(id)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: Product) => {
        return data
      })
  }

  public static async getProductBySlug(slug: string) {
    return await API.getProductBySlug(slug)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: Product) => {
        return data
      })
  }

  public static async getFilters(id: number) {
    return await API.getFilters(id)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: FilterCategory[]) => {
        return data
      })
  }

  public static async postFeedback(dto: Dto) {
    return await API.postFeedback(dto)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data) => {
        return data
      })
  }

  public static async getMeta(dto: Dto) {
    return await API.getMeta(dto)
      .then((response: APIResponse<any>) => {
        return response.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: MetaTag) => {
        return data
      })
  }

  public static getCities() {
    return API.getCities()
      .then((response: APIResponse<any>) => {
        return response?.data
      })
      .catch((error) => {
        throw new Error(error)
      })
      .then((data: City[]) => {
        return data
      })
  }
}
