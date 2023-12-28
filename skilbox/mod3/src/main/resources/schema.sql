DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS authors_books;

CREATE TABLE  books(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       author_id BIGINT NOT NULL,
                       title VARCHAR(250) NOT NULL,
                       price_old INTEGER DEFAULT NULL,
                       price INTEGER DEFAULT NULL
);
CREATE TABLE  authors(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(250) NOT NULL,
                       last_name VARCHAR(250) NOT NULL,
                       patronymic VARCHAR(250) NOT NULL,
                       biography VARCHAR NOT NULL
);
CREATE TABLE  authors_books(
                       author_id BIGINT,
                       books_id BIGINT
);
ALTER TABLE authors_books
    ADD FOREIGN KEY (author_id)
        REFERENCES authors(id);
ALTER TABLE authors_books
    ADD FOREIGN KEY (books_id)
        REFERENCES books(id);

-- authors
-- id
-- first_name
-- last_name
-- patronymic
-- biography

-- books_authors
-- books_id
-- author_id
