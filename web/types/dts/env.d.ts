/// <reference types="vite/client" />

interface ImportMetaEnv {
  apiUrl: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
