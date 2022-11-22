
# Rate My Internship

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
## Installation

1. Download the project file.
2. Install the latest version of Node.js, which can be found [here](https://nodejs.org/en/download/)
3. Open the project in the Java IDE of your choice, navigate to /src/main/java/main/Application.java.
4. Compile and run to start backend Spring server.
5. Open a new terminal window.
6. cd into ~/rate_internship/frontend
7. Run `npm install` to install dependencies.
8. Run `npm start` to start the frontend React server.
9. New tab should open.
