FROM node:12-slim

WORKDIR /usr/src/app

ENV PORT=80

COPY package*.json ./

RUN npm install

COPY . ./

EXPOSE 80

CMD [ "node", "index.js" ]