
DROP TABLE IF EXISTS public.bank CASCADE;
CREATE TABLE public.bank
(
  bank_id bigserial NOT NULL,
  bank_name character varying(50),
  CONSTRAINT bank_pkey PRIMARY KEY (bank_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.bank
  OWNER TO postgres;
DROP TABLE IF EXISTS public.account CASCADE;  
CREATE TABLE public.account
(
  account_id bigserial NOT NULL,
  bank_id bigint,
  account_holder_name character varying(50),
  account_type character varying(10),
  pan_no character varying(15),
  address character varying(100),
  phone character varying(15),
  birth_date date,
  email character varying(40),
  balance bigint,
  pan_img_url character varying(150),
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
DROP TABLE IF EXISTS public.transaction CASCADE; 
CREATE TABLE public.transaction
(
  transaction_id bigserial NOT NULL ,
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
INSERT INTO public.bank(bank_name) VALUES ('Axis');
INSERT INTO public.bank(bank_name) VALUES ('SBI');
INSERT INTO public.bank(bank_name) VALUES ('PNB');
