# ---- Node modules ----- #
FROM node:20.9.0-bullseye-slim AS node_modules

WORKDIR /opt/project

ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
RUN corepack enable

COPY ./package*.json ./pnpm* ./

RUN --mount=type=cache,id=pnpm,target=/pnpm/store pnpm install --frozen-lockfile --ignore-scripts

# ---- Build ------------ #
FROM node:20.9.0-bullseye-slim AS dist

WORKDIR /opt/project

COPY --from=node_modules /opt/project/node_modules ./node_modules
COPY ./ .

RUN npm run lint
RUN npm run build

# ---- Release ---------- #
FROM gcr.io/distroless/nodejs20-debian12

WORKDIR /opt/project

COPY --from=dist /opt/project/.nuxt .nuxt
COPY --from=dist /opt/project/.output .output

ENV HOST 0.0.0.0
CMD [ "./.output/server/index.mjs" ]


