CREATE TABLE author (
    author_id INT,
    author_name VARCHAR(400) NOT NUll,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deleted boolean DEFAULT FALSE,
    CONSTRAINT uk_author_name UNIQUE (author_name),
    CONSTRAINT pk_author PRIMARY KEY (author_id)
);

CREATE SEQUENCE author_sequence 
START WITH 176
INCREMENT BY 1;

CREATE TABLE genre (
    genre_id INT,
    genre_name VARCHAR(400) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deleted boolean DEFAULT FALSE,
    CONSTRAINT uk_genre_name UNIQUE (genre_name),
    CONSTRAINT pk_genre PRIMARY KEY (genre_id)
);

CREATE SEQUENCE genre_sequence 
START WITH 21
INCREMENT BY 1;

CREATE TABLE publisher (
    publisher_id INT,
    publisher_name VARCHAR(400) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deleted boolean DEFAULT FALSE,
    CONSTRAINT uk_publisher_name UNIQUE (publisher_name),
    CONSTRAINT pk_publisher PRIMARY KEY (publisher_id)
);

CREATE SEQUENCE publisher_sequence 
START WITH 51
INCREMENT BY 1;

--CREATE TABLE review (
--	review_id INT,
--    customer_email VARCHAR(350),
--    rating DECIMAL(9,2),
--    review_title VARCHAR(50),
--    review_description VARCHAR(200),
--    CONSTRAINT pk_review PRIMARY KEY (review_id)
--);

create table book (
    book_id INT,
    title VARCHAR(400) NOT NULL,
    `description` varchar(400) NOT NULL,
    isbn13 VARCHAR(13) NOT NULL,
    num_pages INT NOT NULL,
    publication_date DATE NOT NULL,
    publisher_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    deleted boolean DEFAULT FALSE,
    CONSTRAINT chk_book_num_pages CHECK (num_pages >= 1),
    CONSTRAINT uk_book_title UNIQUE (title),
    CONSTRAINT uk_book_isbn13 UNIQUE (isbn13),
    CONSTRAINT pk_book PRIMARY KEY (book_id),
    CONSTRAINT fk_book_pub FOREIGN KEY (publisher_id) REFERENCES publisher (publisher_id)
);

CREATE SEQUENCE book_sequence 
START WITH 183 
INCREMENT BY 1;


CREATE TABLE book_author (
    book_id INT,
    author_id INT,
    CONSTRAINT pk_bookauthor PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_ba_book FOREIGN KEY (book_id) REFERENCES book (book_id),
    CONSTRAINT fk_ba_author FOREIGN KEY (author_id) REFERENCES author (author_id)
);

create table book_genre (
	book_id INT,
    genre_id INT,
    CONSTRAINT pk_bookgenre PRIMARY KEY (book_id, genre_id),
    CONSTRAINT fk_bg_book FOREIGN KEY (book_id) REFERENCES book (book_id),
    CONSTRAINT fk_bg_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id)
);

--create table book_review (
--	book_id INT,
--    review_id INT,
--    CONSTRAINT pk_bookreview PRIMARY KEY (book_id, review_id),
--    CONSTRAINT fk_br_book FOREIGN KEY (book_id) REFERENCES book (book_id),
--    CONSTRAINT fk_br_review FOREIGN KEY (review_id) REFERENCES review (review_id)
--);
