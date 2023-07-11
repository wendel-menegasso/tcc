create table conta(id int primary key, banco int, tipo int, saldo double, agencia varchar(12), conta varchar(12), usuario int);
create table banco(id int primary key, descr varchar(30));
create table cidade (id int primary key, estado int, descr varchar(40));
create table empresa (id int primary key, nome varchar(50), cnpj varchar(14), ie varchar(14), numeroFuncionarios int, nomeFantasia varchar(50), faturamento double, usuario int);
create table estado (id int primary key, descr varchar(40));
create table modelo (id int primary key, fabricante int, descr varchar(30));
create table imoveis (id int primary key, tipo int, valor double, cep int, estado int, cidade int, bairro varchar(50), rua varchar(60), numero int, complemento varchar(50), usuario int);
create table fabricantes (id int primary key, descr varchar(30));
create table despesas (id int primary key, nome varchar(50), valor double, mes int, ano int, dia int, tipo int, repeticao int, origem int, usuario int);
create table repeticao (id int primary key, descr varchar(50));
create table rendas (id int primary key, nome varchar(50), valor double, mes int, ano int, dia int, tipo int, repeticao int, usuario int);
create table veiculos (id int primary key, tipo int, valor double, fabricante int, modelo int, ano int, placa varchar(8), usuario int);
create table usuarios (id int primary key, username varchar(40) not null, senha varchar(200) not null, email varchar(50) not null, token varchar(200) not null, nome varchar(30) not null, sobrenome varchar(50) not null, cpf varchar(11) not null);
create table tipoVeiculos (id int primary key, descr varchar(30) not null);
create table tipoRenda (id int primary key, descr varchar(50) not null);
create table tipoConta (id int primary key, descr varchar(25) not null);
create table tipoDespesas (id int primary key, descr varchar(40) not null);
create table tipoImovel (id int primary key, descr varchar(50) not null);

insert into estado (id, descr) values (1, "Acre (AC)"),
(2, "Alagoas (AL)"),
(3, "Amapá (AP))"),
(5, "Amazonas (AM)"),
(6, "Bahia (BA)"),
(7, "Ceará (CE)"),
(8, "Distrito Federal (DF)"),
(9, "Espírito Santo (ES)"),
(10, "Goiás (GO)"),
(11, "Maranhão (MA)"),
(12, "Mato Grosso do Sul (MS)"),
(13, "Minas Gerais (MG)"),
(14, "Pará (PA)"),
(15, "Paraíba (PB)"),
(16, "Paraná (PR)"),
(17, "Pernambuco (PE)"),
(18, "Piauí (PI)"),
(19, "Rio de Janeiro (RJ)"),
(20, "Rio Grande do Norte (RN)"),
(21, "Rio Grande do Sul (RS)"),
(22, "Rondônia (RO)"),
(23, "Roraima (RR)"),
(24, "Santa Catarina (SC)"),
(25, "São Paulo (SP)"),
(26, " Sergipe (SE)"),
(27, "Tocantins (TO)");