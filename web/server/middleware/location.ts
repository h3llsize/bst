export default defineEventHandler((event) => {
  const headers = getHeaders(event)
  const hostname = headers.host ?? ''
  const parts = hostname.split('.')
  const subdomain = parts.length > 2 ? parts?.[0] ?? 'moscow' : 'moscow'

  event.context.subdomain = subdomain
  event.context.hostname = hostname

  setCookie(event, 'subdomain', subdomain, { sameSite: true })
  setCookie(event, 'host', hostname, { sameSite: true })
})
