CREATE TABLE IF NOT EXISTS produtos (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    codigo_de_barras VARCHAR(13) NOT NULL UNIQUE,
    categoria VARCHAR(255) NOT NULL,
    preco FLOAT
);
