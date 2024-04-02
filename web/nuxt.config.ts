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

  app: {
    head: {
      script: [`
    <!-- Yandex.Metrika counter -->
   <script type="text/javascript" >
   (function(m,e,t,r,i,k,a){m[i]=m[i]||function(){(m[i].a=m[i].a||[]).push(arguments)};
   m[i].l=1*new Date();
   for (var j = 0; j < document.scripts.length; j++) {if (document.scripts[j].src === r) { return; }}
   k=e.createElement(t),a=e.getElementsByTagName(t)[0],k.async=1,k.src=r,a.parentNode.insertBefore(k,a)})
   (window, document, "script", "https://mc.yandex.ru/metrika/tag.js", "ym");

   ym(96919439, "init", {
        clickmap:true,
        trackLinks:true,
        accurateTrackBounce:true,
        webvisor:true
   });
  </script>
  <noscript><div><img src="https://mc.yandex.ru/watch/96919439" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
  <!-- /Yandex.Metrika counter -->
  `],
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
