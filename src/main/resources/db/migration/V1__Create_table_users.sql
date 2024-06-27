CREATE TABLE departament (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
--rollback DROP TABLE departament;

INSERT INTO departament
(id, "name")
VALUES(nextval('departament_id_seq'::regclass), 'admin');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    departament_id INTEGER,
    FOREIGN KEY (departament_id) REFERENCES departament(id)
);
--rollback DROP TABLE users;

INSERT INTO users
(id, "name", email, departament_id)
VALUES(nextval('users_id_seq'::regclass), 'users_default', 'users_default@management', 1);