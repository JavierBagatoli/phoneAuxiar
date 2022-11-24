FROM openjdk:11
COPY ./target/persona-0.0.1-SNAPSHOT.jar /tmp/api-persona.jar
WORKDIR /tmp
CMD ["java" , "-jar","api-persona.jar"]

- uses: docker/build-push-action@v2
        with:
          context: .
          push: true
          tags: ghcr.io/org/repo:tag
          cache-from: type=gha
          cache-to: type=gha,mode=max