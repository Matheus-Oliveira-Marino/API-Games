create database JOGOS;
use JOGOS;
select DATABASE();
SHOW DATABASES;

drop database games;

CREATE TABLE games
 (
  ID INT auto_increment PRIMARY KEY,
  nome VARCHAR(50) not null UNIQUE,
  classificacao VARCHAR(200) not null,
  descricao TEXT not null,
  imagem VARCHAR(300)
);
select * from games;
drop table games;