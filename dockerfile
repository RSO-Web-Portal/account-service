FROM openjdk:8

RUN mkdir /account

WORKDIR /account

COPY . ./account
ADD . /account

EXPOSE 8080

CMD ["java", "-jar", "account/jax-rs-2.5.0-SNAPSHOT.jar"]