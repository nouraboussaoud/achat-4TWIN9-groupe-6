# Étape 1 : Build Angular app avec Node 17
FROM node:17 AS build

# Créer dossier de travail
WORKDIR /app

# Copier package files et installer dépendances
COPY package*.json ./
RUN npm install

# Copier le reste de l'app
COPY . .

# Build avec base-href adapté (ex: /myapp/)
RUN npm run build -- --configuration production --base-href /myapp/

# Étape 2 : Nginx pour servir les fichiers Angular
FROM nginx:alpine

# Supprimer le contenu par défaut de Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copier le build Angular dans le dossier public de Nginx
COPY --from=build /app/dist/achat-frontend /usr/share/nginx/html/myapp

# Copier config nginx si besoin (optionnel)
# COPY nginx.conf /etc/nginx/nginx.conf

# Exposer le port
EXPOSE 80

# Lancer nginx
CMD ["nginx", "-g", "daemon off;"]
