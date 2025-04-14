# frontend/Dockerfile
FROM node:18-alpine as build
WORKDIR /app
COPY . .
RUN npm install && npm run build --configuration production

FROM nginx:alpine
COPY --from=build /app/dist/achat-frontend /usr/share/nginx/html
s
