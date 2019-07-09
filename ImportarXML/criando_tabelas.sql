CREATE TABLE nota
(
  id serial NOT NULL,
  versao character varying(5),
  tipo_nota character varying(3),
  chave character varying(44),
  serie character varying(10),	
  numero character varying(10),	
  data_emissao character varying(30),
  PRIMARY KEY (id)
);

CREATE TABLE item_nota (
 id serial NOT NULL,
 nota_id INTEGER NOT NULL,
 codigo character varying(7),
 nome character varying(150),
 codigo_barra character varying(20),
 ncm character varying(10),
 cest character varying(10),
 cfop character varying(5),
 quantidade character varying(10),
 valor_unitario character varying(10),
 valor_total character varying(10),
 PRIMARY KEY (id),
 FOREIGN KEY (nota_id) REFERENCES nota (id)
);