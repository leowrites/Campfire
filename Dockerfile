# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/

# temp container to build using gradle
FROM gradle:7.5.1-jdk8 AS GRADLE
ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY build.gradle $APP_HOME

COPY gradle $APP_HOME
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

FROM node:16.13.1 AS NODE

RUN  apt-get update && apt-get -y install openjdk-11-jdk
RUN apt-get install -y supervisor

ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY . .
WORKDIR /frontend
COPY ./frontend/package.json package.json
RUN npm install

WORKDIR $APP_HOME
CMD npm start

EXPOSE 8080


# actual container
#FROM amazoncorretto:11
#ENV APP_HOME=/app
#
#WORKDIR $APP_HOME
#COPY . .
#COPY --from=NODE $APP_HOME/frontend $APP_HOME/frontend
#
#WORKDIR frontend
#RUN npm install
#
#EXPOSE 8080
#
#CMD ./gradlew bootRun