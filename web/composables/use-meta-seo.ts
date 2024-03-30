import { useSeoMeta } from '@unhead/vue'
import { MetaPostDTO, Services } from '#/utils/api'
import type { MetaTag } from '#/types/common'

export async function useLoadMeta(params?: Partial<MetaPostDTO>) {
  let meta = {} as MetaTag
  try {
    const metaDto = new MetaPostDTO()

    metaDto.type = params?.type ?? ''
    metaDto.slug = params?.slug ?? ''
    metaDto.city = params?.city
    ?? useCookie('subdomain').value
    ?? useStore().subdomain

    meta = await Services.getMeta(metaDto)
  }
  catch (e) {
    meta.openGraph = {
      title: 'Категория металлопроката',
      description: 'Категория каталога металлопроката',
      image: '',
      locality: 'Пермь',
      locale: 'ru_RU',
      siteName: 'БСТ',
      type: 'website',
    }

    meta.otherDTO = {
      description: 'Категория каталога металлопроката',
      keywords: 'металлопрокат, категория католога, металл, бст',
      title: 'Категория металлопроката',
    }
  }

  const applySeoMeta = () => {
    useSeoMeta({
      themeColor: '#FFFFFF',

      title: meta?.otherDTO?.title,
      description: meta?.otherDTO?.description,
      ogTitle: meta?.openGraph?.title,
      ogDescription: meta?.openGraph?.description,
      ogImage: meta?.openGraph?.image,
      ogSiteName: meta?.openGraph?.siteName,
      ogLocale: meta?.openGraph?.locale,
      ogLocaleAlternate: meta?.openGraph?.locality,
      ogUrl: useRoute().fullPath,

      twitterTitle: meta?.otherDTO?.title,
      twitterDescription: meta?.otherDTO?.description,
      twitterImage: meta?.openGraph?.image,
      twitterCard: 'app',
    })
  }

  return { meta, applySeoMeta, seoMeta: {} }
}
