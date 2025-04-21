Spring AI Demo
===================================

Prerequisitos
============

- Maven
- Java 21
- Docker
- Postman
- Ollama

Ejecución
============

1 - Descarga e instalación de Ollama. Ejecutar el siguiente comando en la terminal:

```
curl -fsSL https://ollama.com/install.sh | sh
```

2 - (Opcional) Descargar un modelo de Ollama y ejecutarlo en el terminal para interactuar con él. Ejecutar el comando en la terminal:

```
ollama run llama3.2:1b
```

3 - (Opcional) Levantar un servidor de Zipkin para revisar las trazas de Spring AI en la aplicación. Ejecutar el siguiente comando:

```
docker run -d -p 9411:9411 openzipkin/zipkin
```

4 - Compilación del código en línea de comando. En la carpeta raíz del proyecto lanzar el siguiente comando:

```
mvn clean install
```

5 - Lanzar el siguiente comando Docker para levantar PGvector:

```
docker run -it --rm --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres pgvector/pgvector:pg16
```


6 - Ejecución de aplicación. En la carpeta raíz del proyecto lanzar el siguiente comando:

```
mvn clean spring-boot:run
```

7 - Ejecutar la colección Postman disponible.


