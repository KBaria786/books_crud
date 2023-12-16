# books-api-service

A Spring Boot based REST API designed for managing a books database. The API interacts with an H2 database, providing functionalities for handling entities such as books, authors, genres and publishers. Validation and pagination mechanisms are implemented to ensure data integrity.

Before running the Spring Boot application, ensure you run the following Maven command:
```
mvn clean install
```
This command generates the JPA metamodel entites which is required for proper functioning of the api.

## Swagger API Documentation
Explore and interact with the API using Swagger documentation. Once the application is up and running, visit the Swagger UI endpoint.
```
http://http://localhost:8080/swagger-ui/index.html
```

## books
Request endpoints to interact with books.

## Find book by id
### Request:
```
http://localhost:8080/books/{id}
```
### Response:
```
{
  "Book id": 1,
  "isbn13": "693067420-6",
  "title": "The Great Gatsby",
  "numPages": 377,
  "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
  "publicationDate": "2022-05-11",
  "publisher": {
    "Publisher id": 22,
    "Publisher name": "Schmidt, Reilly and Sawayn"
  },
  "authors": [
    {
      "Author id": 1,
      "Author name": "F. Scott Fitzgerald"
    }
  ],
  "genres": [
    {
      "Genre id": 1,
      "Genre name": "Fiction"
    }
  ],
  "createdAt": "2023-12-16T06:14:29.671Z",
  "updatedAt": "2023-12-16T06:14:29.671Z",
  "deleted": false
}
```

## Update book by id
### Request:
```
http://localhost:8080/books/{id}
```
### Request Body:
```
{
  "isbn13": "693067420-6",
  "title": "The Great Gatsby",
  "numPages": 377,
  "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
  "publicationDate": "2022-05-11",
  "publisherId": 22,
  "authors": [
    1
  ],
  "genres": [
    1
  ]
}
```

### Response:
```
{
  "Book id": 1,
  "isbn13": "693067420-6",
  "title": "The Great Gatsby",
  "numPages": 377,
  "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
  "publicationDate": "2022-05-11",
  "publisher": {
    "Publisher id": 22,
    "Publisher name": "Schmidt, Reilly and Sawayn"
  },
  "authors": [
    {
      "Author id": 1,
      "Author name": "F. Scott Fitzgerald"
    }
  ],
  "genres": [
    {
      "Genre id": 1,
      "Genre name": "Fiction"
    }
  ],
  "createdAt": "2023-12-16T06:18:42.144Z",
  "updatedAt": "2023-12-16T06:18:42.144Z",
  "deleted": false
}
```
## Mark book as deleted
## Request:
```
http://localhost:8080/books/{id}
```
## Delete book
## Request:
```
http://localhost:8080/books/{id}
```
## Find all books
## Request:
```
http://localhost:8080/books
```
### Response:
```
[
  {
    "Book id": 1,
    "isbn13": "693067420-6",
    "title": "The Great Gatsby",
    "numPages": 377,
    "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
    "publicationDate": "2022-05-11",
    "publisher": {
      "Publisher id": 22,
      "Publisher name": "Schmidt, Reilly and Sawayn"
    },
    "authors": [
      {
        "Author id": 1,
        "Author name": "F. Scott Fitzgerald"
      }
    ],
    "genres": [
      {
        "Genre id": 1,
        "Genre name": "Fiction"
      }
    ],
    "createdAt": "2023-12-16T06:23:28.015Z",
    "updatedAt": "2023-12-16T06:23:28.015Z",
    "deleted": false
  }
]
```
## Create a new book
## Request:
```
http://localhost:8080/books
```
## Request Body:
```
{
  "isbn13": "693067420-6",
  "title": "The Great Gatsby",
  "numPages": 377,
  "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
  "publicationDate": "2022-05-11",
  "publisherId": 22,
  "authors": [
    1
  ],
  "genres": [
    1
  ]
}
```
## Response:
```
{
  "Book id": 1,
  "isbn13": "693067420-6",
  "title": "The Great Gatsby",
  "numPages": 377,
  "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
  "publicationDate": "2022-05-11",
  "publisher": {
    "Publisher id": 22,
    "Publisher name": "Schmidt, Reilly and Sawayn"
  },
  "authors": [
    {
      "Author id": 1,
      "Author name": "F. Scott Fitzgerald"
    }
  ],
  "genres": [
    {
      "Genre id": 1,
      "Genre name": "Fiction"
    }
  ],
  "createdAt": "2023-12-16T06:24:50.819Z",
  "updatedAt": "2023-12-16T06:24:50.819Z",
  "deleted": false
}
```
## Filter books
## Request:
```
http://localhost:8080/books/search?genre_name=Fiction&title_like=The
```
## Response:
```
[
  {
    "id": 1,
    "isbn13": "693067420-6",
    "title": "The Great Gatsby",
    "numPages": 377,
    "description": "The story of Jay Gatsby, a young millionaire who tries to win back his lost love while navigating the excesses and decadence of the roaring twenties.",
    "publicationDate": "2022-05-11",
    "publisher": {
      "id": 22,
      "publisherName": "Schmidt, Reilly and Sawayn"
    },
    "authors": [
      {
        "id": 1,
        "authorName": "F. Scott Fitzgerald"
      }
    ],
    "genres": [
      {
        "id": 1,
        "genreName": "Fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 3,
    "isbn13": "682690891-3",
    "title": "The Catcher in the Rye",
    "numPages": 150,
    "description": "The story of Holden Caulfield, a teenage boy who is expelled from his prep school and wanders around New York City, struggling to come to terms with the adult world.",
    "publicationDate": "2023-03-02",
    "publisher": {
      "id": 23,
      "publisherName": "Purdy, Casper and Cremin"
    },
    "authors": [
      {
        "id": 3,
        "authorName": "J.D. Salinger"
      }
    ],
    "genres": [
      {
        "id": 1,
        "genreName": "Fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 6,
    "isbn13": "901074270-9",
    "title": "The Lord of the Rings",
    "numPages": 429,
    "description": "An epic fantasy novel set in the world of Middle-earth, where a hobbit named Frodo must journey to destroy the One Ring and save the world from the evil Sauron.",
    "publicationDate": "2023-04-28",
    "publisher": {
      "id": 43,
      "publisherName": "Schaden-Rodriguez"
    },
    "authors": [
      {
        "id": 6,
        "authorName": "J.R.R. Tolkien"
      }
    ],
    "genres": [
      {
        "id": 1,
        "genreName": "Fiction"
      },
      {
        "id": 4,
        "genreName": "Fantasy"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 7,
    "isbn13": "562973903-4",
    "title": "The Handmaid's Tale",
    "numPages": 424,
    "description": "A dystopian novel set in the future United States where a totalitarian regime has taken over and women are forced into sexual servitude as 'handmaids.'",
    "publicationDate": "2023-03-31",
    "publisher": {
      "id": 32,
      "publisherName": "Hermiston, Spencer and Bradtke"
    },
    "authors": [
      {
        "id": 7,
        "authorName": "Margaret Atwood"
      }
    ],
    "genres": [
      {
        "id": 1,
        "genreName": "Fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 8,
    "isbn13": "634695478-4",
    "title": "The Kite Runner",
    "numPages": 414,
    "description": "The story of Amir, a young boy from Afghanistan, and his journey of redemption after betraying his best friend Hassan during their childhood.",
    "publicationDate": "2023-03-30",
    "publisher": {
      "id": 9,
      "publisherName": "Dibbert and Sons"
    },
    "authors": [
      {
        "id": 8,
        "authorName": "Khaled Hosseini"
      }
    ],
    "genres": [
      {
        "id": 1,
        "genreName": "Fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 10,
    "isbn13": "107351580-X",
    "title": "The Girl with the Dragon Tattoo",
    "numPages": 394,
    "description": "A crime thriller novel that follows Mikael Blomkvist, a journalist, and Lisbeth Salander, a young hacker, as they investigate the disappearance of a wealthy businessman's niece.",
    "publicationDate": "2023-04-19",
    "publisher": {
      "id": 31,
      "publisherName": "Goodwin, Schmeler and Veum"
    },
    "authors": [
      {
        "id": 10,
        "authorName": "Stieg Larsson"
      }
    ],
    "genres": [
      {
        "id": 6,
        "genreName": "Thriller"
      },
      {
        "id": 1,
        "genreName": "Fiction"
      },
      {
        "id": 5,
        "genreName": "Mystery"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  }
]
```
## Find all books with pagination
## Request:
```
http://localhost:8080/books/paginated?page=2&limit=5
```
## Response:
```
[
  {
    "id": 11,
    "isbn13": "551663565-9",
    "title": "The Immortal Life of Henrietta Lacks",
    "numPages": 498,
    "description": "The true story of a woman named Henrietta Lacks, whose cancer cells were taken without her knowledge in 1951 and have since been used for countless medical breakthroughs.",
    "publicationDate": "2022-12-02",
    "publisher": {
      "id": 9,
      "publisherName": "Dibbert and Sons"
    },
    "authors": [
      {
        "id": 11,
        "authorName": "Rebecca Skloot"
      }
    ],
    "genres": [
      {
        "id": 7,
        "genreName": "Non-fiction"
      },
      {
        "id": 8,
        "genreName": "Biography"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 12,
    "isbn13": "941629071-8",
    "title": "Outliers",
    "numPages": 269,
    "description": "A study of successful people and the factors that contributed to their achievements, including cultural legacy, family background, and individual opportunity.",
    "publicationDate": "2023-04-17",
    "publisher": {
      "id": 7,
      "publisherName": "Wisozk and Sons"
    },
    "authors": [
      {
        "id": 12,
        "authorName": "Malcolm Gladwell"
      }
    ],
    "genres": [
      {
        "id": 7,
        "genreName": "Non-fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 13,
    "isbn13": "351184143-7",
    "title": "The 7 Habits of Highly Effective People",
    "numPages": 246,
    "description": "A self-help book that outlines seven habits that successful people possess, including being proactive, beginning with the end in mind, and seeking first to understand.",
    "publicationDate": "2023-04-26",
    "publisher": {
      "id": 20,
      "publisherName": "Heidenreich, Heathcote and Hettinger"
    },
    "authors": [
      {
        "id": 13,
        "authorName": "Stephen Covey"
      }
    ],
    "genres": [
      {
        "id": 7,
        "genreName": "Non-fiction"
      },
      {
        "id": 9,
        "genreName": "Self-Help"
      },
      {
        "id": 10,
        "genreName": "Business"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 14,
    "isbn13": "793368680-X",
    "title": "Quiet: The Power of Introverts in a World That Can't Stop Talking",
    "numPages": 381,
    "description": "A book that explores the value of introverts in a society that often rewards extroverted traits, and provides insights on how introverts can thrive in their personal and professional lives.",
    "publicationDate": "2022-08-21",
    "publisher": {
      "id": 50,
      "publisherName": "Sawayn-Buckridge"
    },
    "authors": [
      {
        "id": 14,
        "authorName": "Susan Cain"
      }
    ],
    "genres": [
      {
        "id": 7,
        "genreName": "Non-fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  },
  {
    "id": 15,
    "isbn13": "681504275-8",
    "title": "The New Jim Crow: Mass Incarceration in the Age of Colorblindness",
    "numPages": 188,
    "description": "An analysis of the American criminal justice system and its impact on communities of color, arguing that it has created a new system of racial control similar to the Jim Crow laws of the past.",
    "publicationDate": "2022-10-02",
    "publisher": {
      "id": 3,
      "publisherName": "Schmidt, Lind and D'Amore"
    },
    "authors": [
      {
        "id": 15,
        "authorName": "Michelle Alexander"
      }
    ],
    "genres": [
      {
        "id": 7,
        "genreName": "Non-fiction"
      }
    ],
    "createdAt": "2023-05-09T23:42:04Z",
    "updatedAt": "2023-05-09T23:42:04Z",
    "deleted": false
  }
]
```
