DROP DATABASE IF EXISTS db_farmacia;
CREATE DATABASE db_farmacia;
USE db_farmacia;

CREATE TABLE `Adresses` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `carrer` varchar(100) NOT NULL,
  `ciutat` VARCHAR(100) NOT NULL,
  CONSTRAINT UK_Adresses UNIQUE (carrer, ciutat)
);

CREATE TABLE `Farmacia` (
  `cif` VARCHAR(100) PRIMARY KEY,
  `Adresa` int UNIQUE NOT NULL,
  `actiu` boolean default true
);

CREATE TABLE `Farmaceutic` (
  `dni` varchar(50) PRIMARY KEY,
  `nom` varchar(100) NOT NULL,
  `cognom1` varchar(100) NOT NULL,
  `cognom2` varchar(100) NOT NULL,
  `anyLicenciatura` date NOT NULL,
  `actiu` boolean NOT NULL DEFAULT true
);

CREATE TABLE `Emplea` (
  `idEmplea` INT PRIMARY KEY AUTO_INCREMENT,
  `farmacia` VARCHAR(100),
  `farmaceutic` varchar(100),
  CONSTRAINT FK_Emplea_Farmacia FOREIGN KEY (farmacia) REFERENCES Farmacia(cif) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_Emplea_Farmaceutic FOREIGN KEY (farmaceutic) REFERENCES Farmaceutic(dni) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE `Emplea_Historic` (
  `farmacia` VARCHAR(100),
  `farmaceutic` varchar(100),
  PRIMARY KEY (`farmacia`, `farmaceutic`),
  CONSTRAINT FK_Emph1 FOREIGN KEY (farmacia) REFERENCES Farmacia(cif) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_Emph2 FOREIGN KEY (farmaceutic) REFERENCES Farmaceutic(dni) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE `Medicament` (
  `nomComercial` VARCHAR(100) PRIMARY KEY,
  `formula` VARCHAR(100) NOT NULL,
  `actiu` boolean NOT NULL DEFAULT true
);

CREATE TABLE `Venta` (
  `idVenta` integer PRIMARY KEY AUTO_INCREMENT,
  `dataVenta` timestamp NOT NULL DEFAULT (current_timestamp()),
  `preu` double NOT NULL,
  `farmacia` VARCHAR(100),
  `farmaceutic` VARCHAR(100),
  `medicament` VARCHAR(100),
  CONSTRAINT FK_Venta_Farmacia FOREIGN KEY (farmacia) REFERENCES Farmacia(cif) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_Venta_Farmaceutic FOREIGN KEY (farmaceutic) REFERENCES Farmaceutic(dni) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_Venta_Medicament FOREIGN KEY (medicament) REFERENCES medicament(nomComercial) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE `Pacient` (
  `dni` varchar(100) PRIMARY KEY,
  `nom` varchar(100) NOT NULL,
  `cognom1` varchar(100) NOT NULL,
  `cognom2` varchar(100) NOT NULL,
  `actiu` boolean NOT NULL DEFAULT true
);

CREATE TABLE `Especialitat` (
  `idEspecialitat` integer PRIMARY KEY AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL
);

CREATE TABLE `Metge` (
  `numColegiat` integer PRIMARY KEY AUTO_INCREMENT,
  `especialitat` integer,
  `nom` varchar(100) NOT NULL,
  `cognom1` varchar(100) NOT NULL,
  `cognom2` varchar(100) NOT NULL,
  `actiu` boolean NOT NULL DEFAULT true,
  CONSTRAINT FK_Metge_Esp FOREIGN KEY (especialitat) REFERENCES Especialitat(idEspecialitat) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE `Tracta` (
  `idTracta` integer PRIMARY KEY AUTO_INCREMENT,
  `metge` integer,
  `pacient` varchar(100),
  CONSTRAINT FK_TrMet FOREIGN KEY (metge) REFERENCES Metge(numColegiat) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_TrPac FOREIGN KEY (pacient) REFERENCES Pacient(dni) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE `Prescripcio` (
  `idPrescripcio` integer PRIMARY KEY AUTO_INCREMENT,
  `medicament` VARCHAR(100),
  `metge` integer,
  `pacient` VARCHAR(100),
  `dataPrescripcio` timestamp NOT NULL DEFAULT (current_timestamp()),
  CONSTRAINT FK_Pre_Medicament FOREIGN KEY (medicament) REFERENCES Medicament(nomComercial) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_Pre_Metge FOREIGN KEY (metge) REFERENCES Metge(numColegiat) ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT FK_Pre_Pacient FOREIGN KEY (pacient) REFERENCES Pacient(dni) ON UPDATE CASCADE ON DELETE NO ACTION
);
