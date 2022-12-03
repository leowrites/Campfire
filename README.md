
# Rate My Internship

[![Rate my Intern CI](https://github.com/CSC207-2022F-UofT/rate-my-intern/actions/workflows/rate-my-intern.yml/badge.svg)](https://github.com/CSC207-2022F-UofT/rate-my-intern/actions/workflows/rate-my-intern.yml)

Primarily for prospective ASIP stream University of Toronto students to learn more about their internships of interests based on reviews left by ASIP students who completed their ASIP program.

Secondary stakeholders may include companies who can view the experiences of their interns, and respond to reviews/comments left by students, or other miscellaneous students not in ASIP stream to collaborate as well.


## API Reference

UNDER CONSTRUCTION

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Authors

- [@leowrites](https://github.com/leowrites)
- [@MinGi-K](https://github.com/MinGi-K)
- [@justinwli930](https://github.com/justinwli930)
- [@hanyuxin02](https://github.com/hanyuxin02)
- [@JTP001](https://github.com/JTP001)
- [@PrimeAce123](https://github.com/PrimeAce123)

## Docker
Make sure you have docker installed

In the root directory, run
```bash
docker compose up -d --build
```
To run the tests, run
```bash
docker compose run --rm test
```


## Tech Stack

**Client:** React, MUI

**Server:** Java, Spring

**Database:** PostgreSQL

## Installation

Download the project directory or directly clone the project:

```bash
git clone https://github.com/CSC207-2022F-UofT/rate-my-intern.git
```

```bash
cd frontend
npm install
```

## Run Locally
To start our app, run
```bash
./gradlew bootRun
cd frontend
npm start
```
