![Logo SoftWear](docs/full-logo-white-bg.jpeg)

# Softwear

This projects is a fork made to test a conteiner load over the system resources. It have been refactored to remove unused resources and api routes.

This is the [original repository](https://github.com/CodeURJC-DAW-2021-22/webapp5b).

## Development environment

Backend and frontend will be initialised separately and will communicate with each other via REST api. In order to do it, we set a proxy which let frontend to get all needed information from backend. It's configuration is in proxy.conf.json.

### Configure Postgres db

version: 15 >= 

- **db name**: softwear
- **username**: postgres
- **password**: password

#### Launch Postgres in docker container

```bash
docker run --name sw-pg -d \
    -e POSTGRES_DB=softwear \
    -e POSTGRES_PASSWORD=password \
    -p 5432:5432 \
    postgres:15
```

### Launch Spring Boot

version: 2.6.4

```bash
mvn spring-boot:run
```

### Launch Angular aplication

version: 13 >=

```bash
ng serve --proxy-config proxy.conf.json
```
or 
```bash
npm start 
```
We can use this command instead because it was configure as a script in package.json.

### Launch spring-boot aplication

## Docker

### Prerequisite

- docker
- docker-compose
- set **MAILER_EMAIL** environment variable. Mail address
- set **MAILER_PASS** environment variable. Mail password

### Build the image

Launch the next command from docker directory.
```bash
./create_image.sh
```

### Application Execution - Docker Compose

Launch the next command from docker directory.
```bash
docker-compose up
```
