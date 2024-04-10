CREATE TABLE Utente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    data_di_registrazione DATE NOT NULL DEFAULT CURRENT_DATE
)

CREATE TABLE messaggio (
    id_message SERIAL PRIMARY KEY,
    sender_message VARCHAR(100),
    text TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    isPrivate BOOLEAN NOT NULL DEFAULT FALSE
);