-- Table: public.account

-- DROP TABLE public.account;

CREATE TABLE public.account
(
  account_id bigint NOT NULL DEFAULT nextval('account_account_id_seq'::regclass),
  bank_id bigint,
  account_holder_name character varying(50),
  account_type character varying(10),
  pan_no character varying(15),
  address character varying(100),
  phone character varying(15),
  birth_date date,
  email character varying(40),
  balance bigint,
  CONSTRAINT account_pkey PRIMARY KEY (account_id),
  CONSTRAINT bank_id_fkey FOREIGN KEY (bank_id)
      REFERENCES public.bank (bank_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.account
  OWNER TO postgres;
