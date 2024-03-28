import { resolve } from 'node:path'

import svgLoader from 'vite-svg-loader'

export default defineNuxtConfig({
  ssr: true,
  experimental: {
    appManifest: true
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

  modules: ['@pinia/nuxt'],

  css: ['#/assets/scss/global.scss'],

  alias: {
    '#': resolve(__dirname, '.'),
  },
})
