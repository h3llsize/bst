export interface MetaDto {
  serialize: () => Record<string, string>[]
}

export class OpenGraphMetaDTO implements MetaDto {
  title: string | null = null
  description: string | null = null
  type: string | null = null
  image: string | null = null
  url: string | null = null
  locale: string | null = null
  lacality: string | null = null
  site_name: string | null = null

  serialize(): Record<string, string>[] {
    const dto: Record<string, string> = Object.assign(this)
    const metaTags: Record<string, string>[] = []

    Object.keys(dto).forEach((key) => {
      if (dto[key] === undefined || dto[key] === null)
        delete dto[key]

      metaTags.push({
        property: `og:${key}`,
        content: dto[key],
      })
    })

    return metaTags
  }
}

export class OtherMetaDTO implements MetaDto {
  keywords: string | null = null
  description: string | null = null
  title: string | null = null

  serialize(): Record<string, string>[] {
    const dto: Record<string, string> = Object.assign(this)
    const metaTags: Record<string, string>[] = []

    Object.keys(dto).forEach((key) => {
      if (dto[key] === undefined || dto[key] === null)
        delete dto[key]

      metaTags.push({
        name: key,
        content: dto[key],
      })
    })

    return metaTags
  }
}

export class MetaService {
  constructor(private dto: MetaDto) {}

  public setMetaTags() {
    const metas: HTMLMetaElement[] = []

    this.dto.serialize().forEach((meta) => {
      const el = this.createMetaTag(meta)
      metas.push(el)
      document.head.prepend(el)
    })

    return metas
  }

  public createMetaTag(attributes: Record<string, string>) {
    const meta = document.createElement('meta')

    Object.keys(attributes).forEach((key) => {
      meta.setAttribute(key, attributes[key])
    })

    return meta
  }

  public changeMetaTags(metas: HTMLMetaElement[]) {
    this.dto.serialize().forEach((metaAttr) => {
      const attrName = Object.keys(metaAttr).filter(el => el !== 'content')[0]
      const index = metas.findIndex(element => element.hasAttribute(attrName) && element.getAttribute(attrName) === metaAttr[attrName])
      Object.keys(metaAttr).forEach((key) => {
        metas[index].setAttribute(key, metaAttr[key])
      })
    })
  }
}
