# ADD in application.properties
#---------------------------------
spring.datasource.url=jdbc:h2:file:/tmp/api-persona
spring.datasource.username=api

spring.datasource.password=api
spring.h2.console.enabled=true
spring.h2.console.path=/db-h2
spring.h2.console.settings.trace=true
spring.h2.console.settings.web-allow-others=true


# BUILD APP creating .jar & (ignoring Tests)
-------------------------------

mvn clean package -Dmaven.test.skip=true

#TEST api locally
------------------

java -jar {localpath}/target/persona-0.0.1-SNAPSHOT.jar

# BUILD DOCKER IMAGE 
---------------------

docker build -t api-persona .

# CHECK DOCKER IMAGE
----------------------

docker images

ex.
---
REPOSITORY      TAG         IMAGE ID            CREATED             SIZE
api-persona     latest      570e20b156e8        14 seconds ago      567MB


#  RUN DOCKER CONTAINER
------------------------
docker run --name api-persona -itd -p 9001:9001 api-persona

#  ATTACH DOCKER CONTAINER
------------------------
docker attach api-persona

#  STOP DOCKER CONTAINER
------------------------
docker stop api-persona

#  START DOCKER CONTAINER
------------------------
docker start api-persona


#  CONNECT TO H2 DB
------------------------

http://{IP}:port/db-h2/

Driver Class:   org.h2.Driver
JDBC URL:       jdbc:h2:/tmp/api-persona
User Name:      api
Password:       api



#  DEPLOY ON PLAY-WITH-DOCKER [https://labs.play-with-docker.com/]
-----------------------------

git clone https://github.com/tpautnfrm/api-persona.git

# https://hub.docker.com/_/maven?tab=description 

cd api-persona

docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean package -Dmaven.test.skip=true

docker build -t "api-persona" .

docker run --name "api-persona" -d -p 9001:9001 api-persona