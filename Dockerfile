# download java 11 image and gradle
FROM gradle:7.5.1-jdk11


RUN apt-get update && apt-get install -y curl gnupg
RUN curl -sL https://deb.nodesource.com/setup_14.x | bash -
RUN apt-get install -y nodejs

WORKDIR /app

# copy gradle files to container
COPY build.gradle gradlew gradlew.bat ./

# copy over all files in src from the root directory to the working directory
COPY src ./src

# copy over everything other than node_modules from frontend to frontend
COPY frontend ./frontend

# install dependencies for frontend
RUN cd frontend && npm install

# build frontend
RUN cd frontend && npm run build

RUN gradle wrapper

# build gradle project
RUN ./gradlew build

EXPOSE 8080

# run the jar file
CMD ./gradlew bootRun

