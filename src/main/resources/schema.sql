CREATE TABLE IF NOT EXISTS hello_world_translations
(
    id            SERIAL PRIMARY KEY,
    language_code VARCHAR(10)  NOT NULL,
    translation   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles    VARCHAR(10)  NOT NULL
);


