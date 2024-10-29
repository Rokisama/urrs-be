CREATE TABLE events (
    ID INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    created_on TIMESTAMP NOT NULL,
    created_by INT NOT NULL
);

INSERT INTO events (title, description, location, event_date, created_on, created_by) VALUES
('Rugsejo pirmos svente', 'Labai gera svente', 'SRK-1', '2024-09-01 10:00:00.000', '2024-08-29 10:31:14.344', 1)