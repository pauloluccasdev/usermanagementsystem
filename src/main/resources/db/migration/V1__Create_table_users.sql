CREATE TABLE departament (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
--rollback DROP TABLE departament;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    departament_id INTEGER,
    FOREIGN KEY (departament_id) REFERENCES departament(id)
);
--rollback DROP TABLE users;
