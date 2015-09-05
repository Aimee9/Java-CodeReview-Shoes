--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: brands; Type: TABLE; Schema: public; Owner: oem; Tablespace: 
--

CREATE TABLE brands (
    id integer NOT NULL,
    label character varying
);


ALTER TABLE brands OWNER TO oem;

--
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: oem
--

CREATE SEQUENCE brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brand_id_seq OWNER TO oem;

--
-- Name: brand_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oem
--

ALTER SEQUENCE brand_id_seq OWNED BY brands.id;


--
-- Name: brands_stores; Type: TABLE; Schema: public; Owner: oem; Tablespace: 
--

CREATE TABLE brands_stores (
    id integer NOT NULL,
    brand_id integer,
    store_id integer
);


ALTER TABLE brands_stores OWNER TO oem;

--
-- Name: brands_stores_id_seq; Type: SEQUENCE; Schema: public; Owner: oem
--

CREATE SEQUENCE brands_stores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE brands_stores_id_seq OWNER TO oem;

--
-- Name: brands_stores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oem
--

ALTER SEQUENCE brands_stores_id_seq OWNED BY brands_stores.id;


--
-- Name: stores; Type: TABLE; Schema: public; Owner: oem; Tablespace: 
--

CREATE TABLE stores (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stores OWNER TO oem;

--
-- Name: store_id_seq; Type: SEQUENCE; Schema: public; Owner: oem
--

CREATE SEQUENCE store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE store_id_seq OWNER TO oem;

--
-- Name: store_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oem
--

ALTER SEQUENCE store_id_seq OWNED BY stores.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: oem
--

ALTER TABLE ONLY brands ALTER COLUMN id SET DEFAULT nextval('brand_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: oem
--

ALTER TABLE ONLY brands_stores ALTER COLUMN id SET DEFAULT nextval('brands_stores_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: oem
--

ALTER TABLE ONLY stores ALTER COLUMN id SET DEFAULT nextval('store_id_seq'::regclass);


--
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oem
--

SELECT pg_catalog.setval('brand_id_seq', 11, true);


--
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: oem
--

COPY brands (id, label) FROM stdin;
9	Nike
10	Converse
11	Adidas
\.


--
-- Data for Name: brands_stores; Type: TABLE DATA; Schema: public; Owner: oem
--

COPY brands_stores (id, brand_id, store_id) FROM stdin;
29	9	8
31	9	11
32	9	10
\.


--
-- Name: brands_stores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oem
--

SELECT pg_catalog.setval('brands_stores_id_seq', 32, true);


--
-- Name: store_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oem
--

SELECT pg_catalog.setval('store_id_seq', 11, true);


--
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: oem
--

COPY stores (id, name) FROM stdin;
8	Shoepie
9	Forrest's Big Foot
10	Monkey Feet
11	Drumline
\.


--
-- Name: brand_pkey; Type: CONSTRAINT; Schema: public; Owner: oem; Tablespace: 
--

ALTER TABLE ONLY brands
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id);


--
-- Name: brands_stores_pkey; Type: CONSTRAINT; Schema: public; Owner: oem; Tablespace: 
--

ALTER TABLE ONLY brands_stores
    ADD CONSTRAINT brands_stores_pkey PRIMARY KEY (id);


--
-- Name: store_pkey; Type: CONSTRAINT; Schema: public; Owner: oem; Tablespace: 
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT store_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: oem
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM oem;
GRANT ALL ON SCHEMA public TO oem;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

