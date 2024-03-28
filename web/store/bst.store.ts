import type { CartProduct, City, MainCategory } from '#/types/models'
import { Services } from '~/utils/api'

interface State {
  subdomain: string
  host: string
  cart: CartProduct[]
  metas: HTMLMetaElement[]
  cities: City[]
  categories: MainCategory[]
}

function getCart() {
  const cart = useCookie(CART_KEY).value

  return cart ? JSON.parse(cart) : []
}

export const useStore = defineStore('bst-store', {
  state: (): State => ({
    subdomain: '',
    host: '',
    cart: getCart(),
    metas: [],
    cities: [],
    categories: [],
  }),
  getters: {
    getCart(): CartProduct[] {
      const cart = this.cart.filter(product => product.amount > 0)
      this.cart = cart

      useCookie(CART_KEY).value = JSON.stringify(this.cart)

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
    setDomainData(subdomain: string, host: string) {
      this.subdomain = subdomain
      this.host = host
    },
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

      useCookie(CART_KEY).value = JSON.stringify(this.cart)
    },
    deleteProduct(id: number) {
      this.cart = this.cart.filter(product => product.id !== id)
      useCookie(CART_KEY).value = JSON.stringify(this.cart)
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
      useCookie(CART_KEY).value = JSON.stringify(this.cart)
      return this.cart
    },
    addCities(cities: City[]) {
      this.cities = cities
    },
    getCategories(categories: MainCategory[]) {
      this.categories = categories
    },
  },
})
