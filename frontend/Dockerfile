# Étape 1 : Build Angular app avec Node 17
FROM node:17 AS build

WORKDIR /app

# Copier package.json et installer dépendances
COPY package*.json ./
RUN npm install

# Copier le reste du code source Angular
COPY . .

# Build de l'application (en production)
RUN npm run build -- --configuration production

# Étape 2 : Nginx pour servir l'app Angular
FROM nginx:alpine

# Supprimer les fichiers HTML par défaut
RUN rm -rf /usr/share/nginx/html/*

# Copier les fichiers buildés d'Angular
COPY --from=build /app/dist/achat-frontend /usr/share/nginx/html

# Ajouter une configuration Nginx personnalisée pour Angular routing
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

# Exposer le port
EXPOSE 80

# Démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]