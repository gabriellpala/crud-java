-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.36-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5332
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para transportadora
DROP DATABASE IF EXISTS `transportadora`;
CREATE DATABASE IF NOT EXISTS `transportadora` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `transportadora`;

-- Copiando estrutura para tabela transportadora.destino
DROP TABLE IF EXISTS `destino`;
CREATE TABLE IF NOT EXISTS `destino` (
  `codDestino` int(11) NOT NULL AUTO_INCREMENT,
  `frete` double DEFAULT NULL,
  `valorEncomenda` double NOT NULL,
  `TRANSPORTADORA_codTransportadora` int(11) NOT NULL,
  `LOJA_DE_DESTINO_codLoja` int(11) NOT NULL,
  PRIMARY KEY (`codDestino`,`TRANSPORTADORA_codTransportadora`,`LOJA_DE_DESTINO_codLoja`),
  KEY `fk_DESTINO_TRANSPORTADORA1_idx` (`TRANSPORTADORA_codTransportadora`),
  KEY `fk_DESTINO_LOJA_DE_DESTINO1_idx` (`LOJA_DE_DESTINO_codLoja`),
  CONSTRAINT `fk_DESTINO_LOJA_DE_DESTINO1` FOREIGN KEY (`LOJA_DE_DESTINO_codLoja`) REFERENCES `loja_de_destino` (`codLoja`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DESTINO_TRANSPORTADORA1` FOREIGN KEY (`TRANSPORTADORA_codTransportadora`) REFERENCES `transportadora` (`codTransportadora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela transportadora.destino: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `destino` DISABLE KEYS */;
INSERT INTO `destino` (`codDestino`, `frete`, `valorEncomenda`, `TRANSPORTADORA_codTransportadora`, `LOJA_DE_DESTINO_codLoja`) VALUES
	(7, 160, 500, 3, 1),
	(8, 50, 300, 2, 3);
/*!40000 ALTER TABLE `destino` ENABLE KEYS */;

-- Copiando estrutura para tabela transportadora.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  `TRANSPORTADORA_codTransportadora` int(11) NOT NULL,
  PRIMARY KEY (`codFuncionario`,`TRANSPORTADORA_codTransportadora`),
  KEY `fk_FUNCIONARIO_TRANSPORTADORA_idx` (`TRANSPORTADORA_codTransportadora`),
  CONSTRAINT `fk_FUNCIONARIO_TRANSPORTADORA` FOREIGN KEY (`TRANSPORTADORA_codTransportadora`) REFERENCES `transportadora` (`codTransportadora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela transportadora.funcionario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` (`codFuncionario`, `nome`, `cpf`, `rg`, `salario`, `TRANSPORTADORA_codTransportadora`) VALUES
	(1, 'Funcionario 1', '111', '111', 111, 1),
	(2, 'Funcionario 2', '222', '222', 22, 2),
	(3, 'Funcionario 3', '333', '333', 3, 1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;

-- Copiando estrutura para tabela transportadora.loja_de_destino
DROP TABLE IF EXISTS `loja_de_destino`;
CREATE TABLE IF NOT EXISTS `loja_de_destino` (
  `codLoja` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `uf` char(2) DEFAULT NULL,
  PRIMARY KEY (`codLoja`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela transportadora.loja_de_destino: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `loja_de_destino` DISABLE KEYS */;
INSERT INTO `loja_de_destino` (`codLoja`, `nome`, `endereco`, `cidade`, `uf`) VALUES
	(1, 'Loja 1', 'Rua 1', 'Cidade 1', '11'),
	(2, 'Loja 2', 'Rua 2', 'Cidade 2', '22'),
	(3, 'Loja 3', 'Rua 3', 'Cidade 3', '33');
/*!40000 ALTER TABLE `loja_de_destino` ENABLE KEYS */;

-- Copiando estrutura para tabela transportadora.transportadora
DROP TABLE IF EXISTS `transportadora`;
CREATE TABLE IF NOT EXISTS `transportadora` (
  `codTransportadora` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `uf` char(2) DEFAULT NULL,
  PRIMARY KEY (`codTransportadora`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela transportadora.transportadora: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `transportadora` DISABLE KEYS */;
INSERT INTO `transportadora` (`codTransportadora`, `nome`, `endereco`, `cidade`, `uf`) VALUES
	(1, 'Transportadora 1', 'Rua 1', 'Cidade 1\r\n', '11'),
	(2, 'Transportadora 2', 'Rua 2', 'Cidade 2', '22'),
	(3, 'Transportadora 36', 'Rua 3', 'Cidade 3', '33');
/*!40000 ALTER TABLE `transportadora` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
