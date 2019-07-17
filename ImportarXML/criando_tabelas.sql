CREATE TABLE nota
(
  id serial NOT NULL,
  versao character varying(5),
  tipo_nota character varying(3),
  chave character varying(44),
  serie character varying(20),	
  numero character varying(20),	
  data_emissao character varying(30),
  PRIMARY KEY (id)
);

CREATE TABLE item_nota (
 id serial NOT NULL,
 nota_id INTEGER NOT NULL,
 codigo character varying(7),
 nome character varying(200),
 codigo_barra character varying(20),
 ncm character varying(20),
 cest character varying(20),
 cfop character varying(5),
 quantidade character varying(20),
 valor_unitario character varying(20),
 valor_total character varying(20),
 PRIMARY KEY (id),
 FOREIGN KEY (nota_id) REFERENCES nota (id)
);