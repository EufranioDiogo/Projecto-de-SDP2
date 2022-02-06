CREATE TABLE localidade(
  pk_localidade INTEGER NOT NULL PRIMARY KEY,
  designacao VARCHAR(200) NOT NULL,
  fk_localidade INTEGER NOT NULL REFERENCES localidade(pk_localidade) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE sexo(
  pk_sexo SERIAL NOT NULL PRIMARY KEY,
  designacao VARCHAR(2) NOT NULL
);

CREATE TABLE portfolio(
  pk_portfolio VARCHAR(200) NOT NULL PRIMARY KEY,
  designacao VARCHAR(200) NOT NULL,
  fk_portfolio VARCHAR(200) DEFAULT NULL 
);

CREATE TABLE tipo_veiculo(
  pk_tipo_veiculo SERIAL NOT NULL PRIMARY KEY,
  designacao VARCHAR(200) NOT NULL
);

CREATE TABLE veiculo(
  pk_veiculo SERIAL NOT NULL PRIMARY KEY,
  url_imagem VARCHAR(250),
  descricao VARCHAR(200) NOT NULL,
  fk_tipo_veiculo INTEGER NOT NULL REFERENCES tipo_veiculo(pk_tipo_veiculo) ON DELETE CASCADE ON UPDATE CASCADE,
  fk_portfolio VARCHAR(200) NOT NULL REFERENCES portfolio(pk_portfolio) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pessoa(
  pk_pessoa SERIAL NOT NULL PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  data_nasc DATE NOT NULL,
  fk_sexo INTEGER NOT NULL REFERENCES sexo(pk_sexo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE endereco(
  pk_endereco SERIAL NOT NULL PRIMARY KEY,
  numero_casa VARCHAR,
  rua VARCHAR(200),
  fk_pessoa INTEGER NOT NULL REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE,
  fk_localidade INTEGER NOT NULL REFERENCES localidade(pk_localidade) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tipo_conta(
  pk_tipo_conta INTEGER NOT NULL PRIMARY KEY,
  designacao VARCHAR(200) NOT NULL
);

CREATE TABLE conta(
  pk_conta SERIAL NOT NULL PRIMARY KEY,
  username VARCHAR(200) NOT NULL UNIQUE,
  password VARCHAR(250) NOT NULL,
  email VARCHAR(200) NOT NULL UNIQUE,
  fk_pessoa INTEGER NOT NULL REFERENCES pessoa(pk_pessoa) ON DELETE CASCADE,
  fk_tipo_conta INTEGER NOT NULL REFERENCES tipo_conta(pk_tipo_conta) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE stock(
  pk_stock SERIAL NOT NULL PRIMARY KEY,
  fk_portfolio VARCHAR(200) NOT NULL REFERENCES portfolio(pk_portfolio) ON DELETE CASCADE ON UPDATE CASCADE,
  quant_veiculo_actual INTEGER NOT NULL DEFAULT 1,
  quant_producto_maxima INTEGER NOT NULL DEFAULT 10 CHECK (quant_producto_maxima >= quant_veiculo_actual)
);

CREATE TABLE montra(
  pk_montra SERIAL NOT NULL PRIMARY KEY,
  fk_producto SERIAL NOT NULL REFERENCES veiculo(pk_veiculo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE compra(
  pk_compra INTEGER NOT NULL,
  fk_conta INTEGER NOT NULL REFERENCES conta(pk_conta),
  fk_veiculo INTEGER NOT NULL REFERENCES veiculo(pk_veiculo),
  quant_veiculos INTEGER NOT NULL DEFAULT 1,
  preco_veiculos NUMERIC NOT NULL,
  preco_total_compra NUMERIC NOT NULL,
  desconto NUMERIC,
  imposto NUMERIC,
  data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(pk_compra, fk_conta, fk_veiculo)
);

CREATE TABLE compra_veiculo(
  pk_compra_veiculo SERIAL PRIMARY KEY,
  fk_portfolio VARCHAR(200) NOT NULL REFERENCES portfolio(pk_portfolio) ON DELETE CASCADE ON UPDATE CASCADE,
  valor_compra NUMERIC NOT NULL CHECK (valor_compra > 0),
  quantidade_comprada INTEGER NOT NULL CHECK (quantidade_comprada > 0),
  data_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
