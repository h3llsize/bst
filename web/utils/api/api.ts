import type { UseFetchOptions } from '#app'

import type {
  AsyncData,
  KeysOf,
  PickFrom,
} from '#app/composables/asyncData'

export type TUseFetchReturnType<T> = AsyncData<
  PickFrom<T, KeysOf<T>> | null,
  any
>

export class ApiClient {
  readonly config = useRuntimeConfig()

  readonly baseUrl: string = useRuntimeConfig().public.apiUrl

  constructor() {
    this.baseUrl = useRuntimeConfig().public.apiUrl
  }

  private request<T>(
    url: string,
    options?: UseFetchOptions<T>,
  ): TUseFetchReturnType<T> {
    const subdomain = useCookie('subdomain').value ?? useStore().subdomain

    return useFetch(url, {
      baseURL: `${this.baseUrl}/api/`,
      ...options,
      headers: {
        City: subdomain,
      },
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

export const api = () => new ApiClient()
