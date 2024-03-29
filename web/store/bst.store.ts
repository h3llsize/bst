import type { CartProduct, City, MainCategory } from '#/types/models'

interface State {
  subdomain: string
  host: string
  cart: CartProduct[]
  metas: HTMLMetaElement[]
  cities: City[]
  categories: MainCategory[]
}

export const useStore = defineStore('bst-store', {
  state: (): State => ({
    subdomain: '',
    host: '',
    cart: [],
    metas: [],
    cities: [],
    categories: [],
  }),
  getters: {
    getCart(): CartProduct[] {
      const cart = this.cart.filter(product => product.amount > 0)
      this.cart = cart

      useCookie(CART_KEY, { sameSite: true }).value = JSON.stringify(this.cart)

      return this.cart
    },
    getMetas(): HTMLMetaElement[] {
      return this.metas
    },
    getCities(): City[] {
      return this.cities
    },
  },
  actions: {
    addProduct(id: number, amount: number) {
      const index = this.cart.findIndex(product => product.id === id)

      if (index !== -1) {
        this.cart[index].amount = amount
      }
      else {
        this.cart.push({
          id,
          amount,
        })
      }

      useCookie(CART_KEY, { sameSite: true }).value = JSON.stringify(this.cart)
    },
    deleteProduct(id: number) {
      this.cart = this.cart.filter(product => product.id !== id)
      useCookie(CART_KEY, { sameSite: true }).value = JSON.stringify(this.cart)
    },
    getProduct(id: number): CartProduct {
      const product = this.cart.filter(product => product.id === id)
      if (!product[0]) {
        return {
          id,
          amount: 0,
        }
      }
      return product[0]
    },
    getCartAction(): CartProduct[] {
      this.cart = this.cart.filter(product => product.amount > 0)
      useCookie(CART_KEY, { sameSite: true }).value = JSON.stringify(this.cart)
      return this.cart
    },
    addCities(cities: City[]) {
      this.cities = cities
    },
  },
})
