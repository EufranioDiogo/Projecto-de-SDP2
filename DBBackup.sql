PGDMP         +                 z            ucandb    10.16    10.16 Z    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            ?           1262    25337    ucandb    DATABASE     x   CREATE DATABASE ucandb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE ucandb;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            ?           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12961    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            ?           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            ?            1259    67366    compra    TABLE     ?  CREATE TABLE public.compra (
    pk_compra integer NOT NULL,
    fk_conta integer NOT NULL,
    fk_veiculo integer NOT NULL,
    quant_unitaria_veiculo integer DEFAULT 1 NOT NULL,
    preco_unitario_veiculo numeric NOT NULL,
    preco_total_compra numeric NOT NULL,
    desconto numeric,
    imposto numeric,
    data_compra timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT compra_quant_unitaria_veiculo_check CHECK ((quant_unitaria_veiculo = 1))
);
    DROP TABLE public.compra;
       public         postgres    false    3            ?            1259    67387    compra_veiculo    TABLE       CREATE TABLE public.compra_veiculo (
    fk_veiculo integer NOT NULL,
    valor_compra numeric NOT NULL,
    data_compra timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT compra_veiculo_valor_compra_check CHECK ((valor_compra > (0)::numeric))
);
 "   DROP TABLE public.compra_veiculo;
       public         postgres    false    3            ?            1259    67315    conta    TABLE     ?   CREATE TABLE public.conta (
    pk_conta integer NOT NULL,
    username character varying(200) NOT NULL,
    email character varying(200) NOT NULL,
    fk_pessoa integer NOT NULL,
    fk_tipo_conta integer NOT NULL
);
    DROP TABLE public.conta;
       public         postgres    false    3            ?            1259    67313    conta_pk_conta_seq    SEQUENCE     ?   CREATE SEQUENCE public.conta_pk_conta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.conta_pk_conta_seq;
       public       postgres    false    208    3            ?           0    0    conta_pk_conta_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.conta_pk_conta_seq OWNED BY public.conta.pk_conta;
            public       postgres    false    207            ?            1259    67242 
   localidade    TABLE     ?   CREATE TABLE public.localidade (
    pk_localidade integer NOT NULL,
    designacao character varying(200) NOT NULL,
    fk_localidade integer NOT NULL
);
    DROP TABLE public.localidade;
       public         postgres    false    3            ?            1259    67354    montra    TABLE     a   CREATE TABLE public.montra (
    pk_montra integer NOT NULL,
    fk_producto integer NOT NULL
);
    DROP TABLE public.montra;
       public         postgres    false    3            ?            1259    67352    montra_fk_producto_seq    SEQUENCE     ?   CREATE SEQUENCE public.montra_fk_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.montra_fk_producto_seq;
       public       postgres    false    213    3            ?           0    0    montra_fk_producto_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.montra_fk_producto_seq OWNED BY public.montra.fk_producto;
            public       postgres    false    212            ?            1259    67350    montra_pk_montra_seq    SEQUENCE     ?   CREATE SEQUENCE public.montra_pk_montra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.montra_pk_montra_seq;
       public       postgres    false    3    213            ?           0    0    montra_pk_montra_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.montra_pk_montra_seq OWNED BY public.montra.pk_montra;
            public       postgres    false    211            ?            1259    67297    pessoa    TABLE     ?   CREATE TABLE public.pessoa (
    pk_pessoa integer NOT NULL,
    nome character varying(200) NOT NULL,
    data_nasc date NOT NULL,
    fk_sexo integer NOT NULL,
    fk_localidade integer NOT NULL
);
    DROP TABLE public.pessoa;
       public         postgres    false    3            ?            1259    67295    pessoa_pk_pessoa_seq    SEQUENCE     ?   CREATE SEQUENCE public.pessoa_pk_pessoa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.pessoa_pk_pessoa_seq;
       public       postgres    false    3    206            ?           0    0    pessoa_pk_pessoa_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.pessoa_pk_pessoa_seq OWNED BY public.pessoa.pk_pessoa;
            public       postgres    false    205            ?            1259    67255 	   portfolio    TABLE     ?   CREATE TABLE public.portfolio (
    pk_portfolio character varying(200) NOT NULL,
    designacao character varying(200) NOT NULL,
    fk_portfolio character varying(200) DEFAULT NULL::character varying
);
    DROP TABLE public.portfolio;
       public         postgres    false    3            ?            1259    67249    sexo    TABLE     k   CREATE TABLE public.sexo (
    pk_sexo integer NOT NULL,
    designacao character varying(200) NOT NULL
);
    DROP TABLE public.sexo;
       public         postgres    false    3            ?            1259    67247    sexo_pk_sexo_seq    SEQUENCE     ?   CREATE SEQUENCE public.sexo_pk_sexo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.sexo_pk_sexo_seq;
       public       postgres    false    199    3            ?           0    0    sexo_pk_sexo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.sexo_pk_sexo_seq OWNED BY public.sexo.pk_sexo;
            public       postgres    false    198            ?            1259    67337    stock    TABLE     ?   CREATE TABLE public.stock (
    pk_stock integer NOT NULL,
    fk_portfolio character varying(200) NOT NULL,
    quant_veiculo_actual integer DEFAULT 1 NOT NULL,
    quant_product_maxima integer DEFAULT 10 NOT NULL
);
    DROP TABLE public.stock;
       public         postgres    false    3            ?            1259    67335    stock_pk_stock_seq    SEQUENCE     ?   CREATE SEQUENCE public.stock_pk_stock_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.stock_pk_stock_seq;
       public       postgres    false    3    210            ?           0    0    stock_pk_stock_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.stock_pk_stock_seq OWNED BY public.stock.pk_stock;
            public       postgres    false    209            ?            1259    67237 
   tipo_conta    TABLE     w   CREATE TABLE public.tipo_conta (
    pk_tipo_conta integer NOT NULL,
    designacao character varying(200) NOT NULL
);
    DROP TABLE public.tipo_conta;
       public         postgres    false    3            ?            1259    67266    tipo_veiculo    TABLE     {   CREATE TABLE public.tipo_veiculo (
    pk_tipo_veiculo integer NOT NULL,
    designacao character varying(200) NOT NULL
);
     DROP TABLE public.tipo_veiculo;
       public         postgres    false    3            ?            1259    67264     tipo_veiculo_pk_tipo_veiculo_seq    SEQUENCE     ?   CREATE SEQUENCE public.tipo_veiculo_pk_tipo_veiculo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.tipo_veiculo_pk_tipo_veiculo_seq;
       public       postgres    false    3    202            ?           0    0     tipo_veiculo_pk_tipo_veiculo_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.tipo_veiculo_pk_tipo_veiculo_seq OWNED BY public.tipo_veiculo.pk_tipo_veiculo;
            public       postgres    false    201            ?            1259    67274    veiculo    TABLE     }  CREATE TABLE public.veiculo (
    pk_veiculo integer NOT NULL,
    descricao character varying(200) NOT NULL,
    preco numeric DEFAULT 0 NOT NULL,
    matricula_veiculo character varying(200) NOT NULL,
    data_compra_veiculo timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fk_tipo_veiculo integer NOT NULL,
    fk_portfolio character varying(200) NOT NULL
);
    DROP TABLE public.veiculo;
       public         postgres    false    3            ?            1259    67272    veiculo_pk_veiculo_seq    SEQUENCE     ?   CREATE SEQUENCE public.veiculo_pk_veiculo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.veiculo_pk_veiculo_seq;
       public       postgres    false    204    3            ?           0    0    veiculo_pk_veiculo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.veiculo_pk_veiculo_seq OWNED BY public.veiculo.pk_veiculo;
            public       postgres    false    203            ?
           2604    67318    conta pk_conta    DEFAULT     p   ALTER TABLE ONLY public.conta ALTER COLUMN pk_conta SET DEFAULT nextval('public.conta_pk_conta_seq'::regclass);
 =   ALTER TABLE public.conta ALTER COLUMN pk_conta DROP DEFAULT;
       public       postgres    false    207    208    208            ?
           2604    67357    montra pk_montra    DEFAULT     t   ALTER TABLE ONLY public.montra ALTER COLUMN pk_montra SET DEFAULT nextval('public.montra_pk_montra_seq'::regclass);
 ?   ALTER TABLE public.montra ALTER COLUMN pk_montra DROP DEFAULT;
       public       postgres    false    213    211    213            ?
           2604    67358    montra fk_producto    DEFAULT     x   ALTER TABLE ONLY public.montra ALTER COLUMN fk_producto SET DEFAULT nextval('public.montra_fk_producto_seq'::regclass);
 A   ALTER TABLE public.montra ALTER COLUMN fk_producto DROP DEFAULT;
       public       postgres    false    212    213    213            ?
           2604    67300    pessoa pk_pessoa    DEFAULT     t   ALTER TABLE ONLY public.pessoa ALTER COLUMN pk_pessoa SET DEFAULT nextval('public.pessoa_pk_pessoa_seq'::regclass);
 ?   ALTER TABLE public.pessoa ALTER COLUMN pk_pessoa DROP DEFAULT;
       public       postgres    false    205    206    206            ?
           2604    67252    sexo pk_sexo    DEFAULT     l   ALTER TABLE ONLY public.sexo ALTER COLUMN pk_sexo SET DEFAULT nextval('public.sexo_pk_sexo_seq'::regclass);
 ;   ALTER TABLE public.sexo ALTER COLUMN pk_sexo DROP DEFAULT;
       public       postgres    false    199    198    199            ?
           2604    67340    stock pk_stock    DEFAULT     p   ALTER TABLE ONLY public.stock ALTER COLUMN pk_stock SET DEFAULT nextval('public.stock_pk_stock_seq'::regclass);
 =   ALTER TABLE public.stock ALTER COLUMN pk_stock DROP DEFAULT;
       public       postgres    false    209    210    210            ?
           2604    67269    tipo_veiculo pk_tipo_veiculo    DEFAULT     ?   ALTER TABLE ONLY public.tipo_veiculo ALTER COLUMN pk_tipo_veiculo SET DEFAULT nextval('public.tipo_veiculo_pk_tipo_veiculo_seq'::regclass);
 K   ALTER TABLE public.tipo_veiculo ALTER COLUMN pk_tipo_veiculo DROP DEFAULT;
       public       postgres    false    202    201    202            ?
           2604    67277    veiculo pk_veiculo    DEFAULT     x   ALTER TABLE ONLY public.veiculo ALTER COLUMN pk_veiculo SET DEFAULT nextval('public.veiculo_pk_veiculo_seq'::regclass);
 A   ALTER TABLE public.veiculo ALTER COLUMN pk_veiculo DROP DEFAULT;
       public       postgres    false    204    203    204            ?          0    67366    compra 
   TABLE DATA               ?   COPY public.compra (pk_compra, fk_conta, fk_veiculo, quant_unitaria_veiculo, preco_unitario_veiculo, preco_total_compra, desconto, imposto, data_compra) FROM stdin;
    public       postgres    false    214   j       ?          0    67387    compra_veiculo 
   TABLE DATA               O   COPY public.compra_veiculo (fk_veiculo, valor_compra, data_compra) FROM stdin;
    public       postgres    false    215   (j       ?          0    67315    conta 
   TABLE DATA               T   COPY public.conta (pk_conta, username, email, fk_pessoa, fk_tipo_conta) FROM stdin;
    public       postgres    false    208   Ej       ?          0    67242 
   localidade 
   TABLE DATA               N   COPY public.localidade (pk_localidade, designacao, fk_localidade) FROM stdin;
    public       postgres    false    197   bj       ?          0    67354    montra 
   TABLE DATA               8   COPY public.montra (pk_montra, fk_producto) FROM stdin;
    public       postgres    false    213   j       ?          0    67297    pessoa 
   TABLE DATA               T   COPY public.pessoa (pk_pessoa, nome, data_nasc, fk_sexo, fk_localidade) FROM stdin;
    public       postgres    false    206   ?j       ?          0    67255 	   portfolio 
   TABLE DATA               K   COPY public.portfolio (pk_portfolio, designacao, fk_portfolio) FROM stdin;
    public       postgres    false    200   ?j       ?          0    67249    sexo 
   TABLE DATA               3   COPY public.sexo (pk_sexo, designacao) FROM stdin;
    public       postgres    false    199   ?j       ?          0    67337    stock 
   TABLE DATA               c   COPY public.stock (pk_stock, fk_portfolio, quant_veiculo_actual, quant_product_maxima) FROM stdin;
    public       postgres    false    210   ?j       ?          0    67237 
   tipo_conta 
   TABLE DATA               ?   COPY public.tipo_conta (pk_tipo_conta, designacao) FROM stdin;
    public       postgres    false    196   k       ?          0    67266    tipo_veiculo 
   TABLE DATA               C   COPY public.tipo_veiculo (pk_tipo_veiculo, designacao) FROM stdin;
    public       postgres    false    202   -k       ?          0    67274    veiculo 
   TABLE DATA               ?   COPY public.veiculo (pk_veiculo, descricao, preco, matricula_veiculo, data_compra_veiculo, fk_tipo_veiculo, fk_portfolio) FROM stdin;
    public       postgres    false    204   Jk       ?           0    0    conta_pk_conta_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.conta_pk_conta_seq', 1, false);
            public       postgres    false    207            ?           0    0    montra_fk_producto_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.montra_fk_producto_seq', 1, false);
            public       postgres    false    212            ?           0    0    montra_pk_montra_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.montra_pk_montra_seq', 1, false);
            public       postgres    false    211            ?           0    0    pessoa_pk_pessoa_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pessoa_pk_pessoa_seq', 1, false);
            public       postgres    false    205            ?           0    0    sexo_pk_sexo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.sexo_pk_sexo_seq', 1, false);
            public       postgres    false    198            ?           0    0    stock_pk_stock_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.stock_pk_stock_seq', 1, false);
            public       postgres    false    209            ?           0    0     tipo_veiculo_pk_tipo_veiculo_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.tipo_veiculo_pk_tipo_veiculo_seq', 1, false);
            public       postgres    false    201            ?           0    0    veiculo_pk_veiculo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.veiculo_pk_veiculo_seq', 1, false);
            public       postgres    false    203            ?
           2606    67376    compra compra_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (pk_compra, fk_conta, fk_veiculo);
 <   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_pkey;
       public         postgres    false    214    214    214            ?
           2606    67324    conta conta_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_email_key;
       public         postgres    false    208            ?
           2606    67320    conta conta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (pk_conta);
 :   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_pkey;
       public         postgres    false    208            ?
           2606    67322    conta conta_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_username_key;
       public         postgres    false    208            ?
           2606    67246    localidade localidade_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.localidade
    ADD CONSTRAINT localidade_pkey PRIMARY KEY (pk_localidade);
 D   ALTER TABLE ONLY public.localidade DROP CONSTRAINT localidade_pkey;
       public         postgres    false    197            ?
           2606    67360    montra montra_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.montra
    ADD CONSTRAINT montra_pkey PRIMARY KEY (pk_montra);
 <   ALTER TABLE ONLY public.montra DROP CONSTRAINT montra_pkey;
       public         postgres    false    213            ?
           2606    67302    pessoa pessoa_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (pk_pessoa);
 <   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_pkey;
       public         postgres    false    206            ?
           2606    67263    portfolio portfolio_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.portfolio
    ADD CONSTRAINT portfolio_pkey PRIMARY KEY (pk_portfolio);
 B   ALTER TABLE ONLY public.portfolio DROP CONSTRAINT portfolio_pkey;
       public         postgres    false    200            ?
           2606    67254    sexo sexo_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.sexo
    ADD CONSTRAINT sexo_pkey PRIMARY KEY (pk_sexo);
 8   ALTER TABLE ONLY public.sexo DROP CONSTRAINT sexo_pkey;
       public         postgres    false    199            ?
           2606    67344    stock stock_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (pk_stock);
 :   ALTER TABLE ONLY public.stock DROP CONSTRAINT stock_pkey;
       public         postgres    false    210            ?
           2606    67241    tipo_conta tipo_conta_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.tipo_conta
    ADD CONSTRAINT tipo_conta_pkey PRIMARY KEY (pk_tipo_conta);
 D   ALTER TABLE ONLY public.tipo_conta DROP CONSTRAINT tipo_conta_pkey;
       public         postgres    false    196            ?
           2606    67271    tipo_veiculo tipo_veiculo_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.tipo_veiculo
    ADD CONSTRAINT tipo_veiculo_pkey PRIMARY KEY (pk_tipo_veiculo);
 H   ALTER TABLE ONLY public.tipo_veiculo DROP CONSTRAINT tipo_veiculo_pkey;
       public         postgres    false    202            ?
           2606    67284    veiculo veiculo_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_pkey PRIMARY KEY (pk_veiculo);
 >   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT veiculo_pkey;
       public         postgres    false    204                       2606    67377    compra compra_fk_conta_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_fk_conta_fkey FOREIGN KEY (fk_conta) REFERENCES public.conta(pk_conta);
 E   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_fk_conta_fkey;
       public       postgres    false    214    208    2804                       2606    67382    compra compra_fk_veiculo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_fk_veiculo_fkey FOREIGN KEY (fk_veiculo) REFERENCES public.veiculo(pk_veiculo);
 G   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_fk_veiculo_fkey;
       public       postgres    false    214    204    2798            	           2606    67395 -   compra_veiculo compra_veiculo_fk_veiculo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.compra_veiculo
    ADD CONSTRAINT compra_veiculo_fk_veiculo_fkey FOREIGN KEY (fk_veiculo) REFERENCES public.veiculo(pk_veiculo);
 W   ALTER TABLE ONLY public.compra_veiculo DROP CONSTRAINT compra_veiculo_fk_veiculo_fkey;
       public       postgres    false    215    2798    204                       2606    67325    conta conta_fk_pessoa_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_fk_pessoa_fkey FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa(pk_pessoa) ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_fk_pessoa_fkey;
       public       postgres    false    208    206    2800                       2606    67330    conta conta_fk_tipo_conta_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_fk_tipo_conta_fkey FOREIGN KEY (fk_tipo_conta) REFERENCES public.tipo_conta(pk_tipo_conta) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_fk_tipo_conta_fkey;
       public       postgres    false    208    196    2788            ?
           2606    67405 (   localidade localidade_fk_localidade_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.localidade
    ADD CONSTRAINT localidade_fk_localidade_fkey FOREIGN KEY (fk_localidade) REFERENCES public.localidade(pk_localidade) NOT VALID;
 R   ALTER TABLE ONLY public.localidade DROP CONSTRAINT localidade_fk_localidade_fkey;
       public       postgres    false    2790    197    197                       2606    67361    montra montra_fk_producto_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.montra
    ADD CONSTRAINT montra_fk_producto_fkey FOREIGN KEY (fk_producto) REFERENCES public.veiculo(pk_veiculo) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.montra DROP CONSTRAINT montra_fk_producto_fkey;
       public       postgres    false    213    204    2798                       2606    67308     pessoa pessoa_fk_localidade_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_localidade_fkey FOREIGN KEY (fk_localidade) REFERENCES public.localidade(pk_localidade) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_fk_localidade_fkey;
       public       postgres    false    197    2790    206                       2606    67303    pessoa pessoa_fk_sexo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_fk_sexo_fkey FOREIGN KEY (fk_sexo) REFERENCES public.sexo(pk_sexo) ON UPDATE CASCADE ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.pessoa DROP CONSTRAINT pessoa_fk_sexo_fkey;
       public       postgres    false    206    2792    199            ?
           2606    67400 %   portfolio portfolio_fk_portfolio_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.portfolio
    ADD CONSTRAINT portfolio_fk_portfolio_fkey FOREIGN KEY (fk_portfolio) REFERENCES public.portfolio(pk_portfolio) NOT VALID;
 O   ALTER TABLE ONLY public.portfolio DROP CONSTRAINT portfolio_fk_portfolio_fkey;
       public       postgres    false    200    2794    200                       2606    67345    stock stock_fk_portfolio_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_fk_portfolio_fkey FOREIGN KEY (fk_portfolio) REFERENCES public.portfolio(pk_portfolio) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.stock DROP CONSTRAINT stock_fk_portfolio_fkey;
       public       postgres    false    210    200    2794                        2606    67290 !   veiculo veiculo_fk_portfolio_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_fk_portfolio_fkey FOREIGN KEY (fk_portfolio) REFERENCES public.portfolio(pk_portfolio) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT veiculo_fk_portfolio_fkey;
       public       postgres    false    204    2794    200            ?
           2606    67285 $   veiculo veiculo_fk_tipo_veiculo_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_fk_tipo_veiculo_fkey FOREIGN KEY (fk_tipo_veiculo) REFERENCES public.tipo_veiculo(pk_tipo_veiculo) ON UPDATE CASCADE ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT veiculo_fk_tipo_veiculo_fkey;
       public       postgres    false    202    204    2796            ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?      ?      x?????? ? ?     