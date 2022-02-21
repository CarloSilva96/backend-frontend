CREATE TABLE IF NOT EXISTS supermercados_produtos (
    supermercado_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    preco_produto FLOAT NOT NULL,
    PRIMARY KEY (supermercado_id, produto_id)
);

ALTER TABLE supermercados_produtos ADD CONSTRAINT supermercado_id_constraint FOREIGN KEY (supermercado_id) REFERENCES supermercados (id);

ALTER TABLE supermercados_produtos ADD CONSTRAINT produto_id_constraint FOREIGN KEY (produto_id) REFERENCES produtos (id);
