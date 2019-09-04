-- Table: public.transaction

-- DROP TABLE public.transaction;

CREATE TABLE public.transaction
(
  transaction_id bigint NOT NULL DEFAULT nextval('transaction_transaction_id_seq'::regclass),
  "time" timestamp without time zone,
  transaction_type character varying(10),
  account_id bigint,
  amount bigint,
  transaction_via character varying(30),
  balance bigint,
  CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id),
  CONSTRAINT account_fkey FOREIGN KEY (account_id)
      REFERENCES public.account (account_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.transaction
  OWNER TO postgres;
