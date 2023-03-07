<h1 align="center"> Consultar CEP </h1>



## Índice 

[Índice](#índice)

[Descrição do Projeto](#descrição-do-projeto)

[Funcionalidades](#funcionalidades)

[Acesso ao Projeto](#acesso-ao-projeto)

[Criando a base MySQL](#criando-a-base)

[Tecnologias utilizadas](#tecnologias-utilizadas)


## Descrição do Projeto
  Projeto back-end para consulta de CEP, consumindo informações do webservice ViaCEP e armazenando em uma base de dados.

## Funcionalidades
-   Consultar CEP: consulta o cep inserido através do end-point: localhost:8080/cep/-cep a ser consultado.
-   Salva o CEP: ao final da consulta, caso o cep não esteja na base de dados ele é salvo.
-   Buscar por Logradouro: busca os ceps salvos na base de dados pelo logradouro end-point: localhost:8080/logradouro/-logradouro a ser consultado.
-   Buscar por uf: retorna uma lista de todos os ceps salvos na base ao consultar pela uf end-point: localhost:8080/uf/-uf a ser consultada-
-   Buscar por uf paginada: retorna os ceps salvos na base de forma paginada ao consultar pela uf end-point: localhost:8080/ufPaged/-uf a ser consultada-/-numero da pagina-/-quantidade por pagina-

## Acesso ao projeto

Para acessar o projeto, basta fazer download desse [repositório](https://github.com/wesleyluz/Consultar_CEP_S).

## 🛠️ Abrir e rodar o projeto
Esse é um projeto Maven em Java 17, utilizando o Framework Spring 3.0.4,para acessar o projeto, basta abri-lo na sua IDE favorita.
Ao abrir o projeto, verifique na pasta [ConsultaCEP/src/main/resources/](https://github.com/wesleyluz/Consultar_CEP_S/tree/main/ConsultaCEP/src/main/resources) o arquivo application.properties,
verificando se as conexões com o seu banco mysql estão corretas.
Antes de rodar o projeto verifique [Criando a base MySQL](#criando-a-base).
Pronto agora é só rodar, basta executar e acessar os end-points no seu navegador ou no PostMan.

##Criando a base MySQL
No workbench cole o seguinte script:
```
create database CEP;
use CEP;
CREATE TABLE cep(
Id          INT auto_increment primary key,
cep         CHAR(9)       NULL,
logradouro  VARCHAR(500) NULL,
complemento VARCHAR(500) NULL,
bairro      VARCHAR(500) NULL,
localidade  VARCHAR(500) NULL,
uf         	CHAR(2)       NULL,
unidade     BIGINT NULL
);
```

## Tecnologias utilizadas
-   Linguagem: <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" /> Java.
-   FrameWork: <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/devicons/devicon@v2.15.1/devicon.min.css"> Spring.
-   Base de Dados: <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" /> MySQL.
-   `IDE`: Spring Tool Suite.
