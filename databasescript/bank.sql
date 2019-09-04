-- Table: public.bank

-- DROP TABLE public.bank;

CREATE TABLE public.bank
(
  bank_id bigint NOT NULL DEFAULT nextval('bank_bank_id_seq'::regclass),
  bank_name character varying(50),
  CONSTRAINT bank_pkey PRIMARY KEY (bank_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.bank
  OWNER TO postgres;
