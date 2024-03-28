import type { UseFetchOptions } from '#app'

import type {
  AsyncData,
  KeysOf,
  PickFrom,
} from '#app/composables/asyncData'
import { useStore } from "~/store/bst.store";

interface TAPIClientConstructor {
  baseUrl?: string
  simpleFetch?: boolean
}

export type TUseFetchReturnType<T> = AsyncData<
    PickFrom<T, KeysOf<T>> | null,
    any
>

export class ApiClient {
  readonly config = useRuntimeConfig()

  readonly baseUrl: string = this.config.public.apiUrl
  readonly simpleFetch: boolean = false
  static headers: Record<string, string> = {
  }

  constructor({ baseUrl, simpleFetch }: TAPIClientConstructor) {
    if (baseUrl)
      this.baseUrl = baseUrl

    if (simpleFetch)
      this.simpleFetch = simpleFetch
  }

  private request<T>(
    url: string,
    options?: UseFetchOptions<T>,
  ): TUseFetchReturnType<T> {
    const subdomain = useCookie('subdomain').value ?? useStore().subdomain
    return useFetch(url, {
      baseURL: `${this.baseUrl}/api/`,
      ...options,
      //@ts-ignore
      headers: {
        ...ApiClient.headers,
        City: subdomain
      },
    })
  }

  public $request<T>(url: string, options?: UseFetchOptions<T>): Promise<T> {
    return $fetch(url, {
      // @ts-expect-error
      baseURL: `${this.baseUrl}`,
      ...options,
      headers: ApiClient.headers,
    })
  }

  get<T>(url: string, options?: UseFetchOptions<T>): TUseFetchReturnType<T> {
    return this.request(url, { ...options, method: 'GET' })
  }

  post<T>(url: string, options?: UseFetchOptions<T>): TUseFetchReturnType<T> {
    return this.request(url, { ...options, method: 'POST' })
  }

  put<T>(url: string, options?: UseFetchOptions<T>): TUseFetchReturnType<T> {
    return this.request(url, { ...options, method: 'PUT' })
  }

  patch<T>(url: string, options?: UseFetchOptions<T>): TUseFetchReturnType<T> {
    return this.request(url, { ...options, method: 'PATCH' })
  }

  delete<T>(url: string, options?: UseFetchOptions<T>): TUseFetchReturnType<T> {
    return this.request(url, { ...options, method: 'DELETE' })
  }
}

export const api = () => new ApiClient({})
