# ---- Node modules ----- #
FROM oven/bun AS node_modules

WORKDIR /opt/project

COPY ./package*.json ./bun* ./

RUN bun install --ignore-scripts

# ---- Build ------------ #
FROM node:20.12.2-bullseye-slim AS dist

WORKDIR /opt/project

COPY --from=node_modules /opt/project/node_modules node_modules
COPY ./ .

RUN npm run build

# ---- Release ---------- #
FROM gcr.io/distroless/nodejs20-debian12

WORKDIR /opt/project

COPY --from=dist /opt/project/.nuxt .nuxt
COPY --from=dist /opt/project/.output .output

ENV HOST 0.0.0.0
CMD [ "./.output/server/index.mjs" ]


