import { resolve } from 'node:path'

import svgLoader from 'vite-svg-loader'

export default defineNuxtConfig({
  ssr: true,
  experimental: {
    appManifest: true,
  },
  routeRules: {
    '/about': {
      prerender: true,
    },
    '/contacts': {
      prerender: true,
    },
    '/feedback': {
      prerender: true,
    },
    '/error': {
      prerender: true,
    },
  },

  devServer: {
    port: 5173,
  },

  imports: {
    dirs: ['store/', 'utils/'],
  },

  runtimeConfig: {
    public: {
      apiUrl: 'https://bst-sl.ru',
    },
  },

  yandexMetrika: {
    id: '96919439',
    delay: 0,
    cdn: false,
    verification: false, // Verification in Yandex Webmaster
    options: {
      clickmap: true,
      trackLinks: true,
      accurateTrackBounce: true,
      webvisor: true,
    },
  },

  vite: {
    plugins: [svgLoader()],
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: `
            @import "#/assets/scss/mixins/mixins";
          `,
        },
      },
    },
  },

  modules: ['@pinia/nuxt', 'nuxt-yandex-metrika'],

  css: ['#/assets/scss/global.scss'],

  alias: {
    '#': resolve(__dirname, '.'),
  },
})
