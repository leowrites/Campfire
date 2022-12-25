
# Rate My Internship

[![Rate my Intern CI](https://github.com/CSC207-2022F-UofT/rate-my-intern/actions/workflows/rate-my-intern.yml/badge.svg)](https://github.com/CSC207-2022F-UofT/rate-my-intern/actions/workflows/rate-my-intern.yml)

Primarily for prospective ASIP stream University of Toronto students to learn more about their internships of interests based on reviews left by ASIP students who completed their ASIP program.

Secondary stakeholders may include companies who can view the experiences of their interns, and respond to reviews/comments left by students, or other miscellaneous students not in ASIP stream to collaborate as well.

## Authors

- [@leowrites](https://github.com/leowrites)
- [@MinGi-K](https://github.com/MinGi-K)
- [@justinwli930](https://github.com/justinwli930)

## Tech Stack

**Client:** React, MUI

**Server:** Java, Spring

**Database:** PostgreSQL

**Tools:** Docker, Github Actions, Azure

## Installation

Download the project directory or directly clone the project:

```bash
git clone https://github.com/CSC207-2022F-UofT/rate-my-intern.git
```

```bash
cd frontend
npm install
```

## To run tests in Docker
Make sure you have docker installed

In the root directory, run
```bash
docker compose up -d --build
```
To run the tests, run
```bash
docker compose run --rm test
```


## Run Locally
To start our app, run
```bash
./gradlew bootRun
cd frontend
npm start
```
