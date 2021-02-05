CREATE SEQUENCE public.bairro_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER TABLE bairro_seq
  OWNER TO postgres;

CREATE TABLE public.bairro
(
    bai_codigo bigint NOT NULL,
    bai_descricao character varying(100) COLLATE pg_catalog."default" NOT NULL,
    versionnum integer NOT NULL,
    CONSTRAINT bairro_pkey PRIMARY KEY (bai_codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bairro
  OWNER TO postgres;
  
  
CREATE TABLE public.bairro_aud
(
    bai_codigo bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    bai_descricao character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT bairro_aud_pkey PRIMARY KEY (bai_codigo, rev),
    CONSTRAINT fka4bdfd366881b858 FOREIGN KEY (rev)
        REFERENCES public.revinfo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bairro_aud
  OWNER TO postgres;