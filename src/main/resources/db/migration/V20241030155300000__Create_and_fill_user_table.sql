CREATE TABLE users (
                        ID INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL
);

INSERT INTO users (first_name, last_name, email, password) VALUES
    ('John', 'Doe', 'john.doe@gmail.com', 'test')