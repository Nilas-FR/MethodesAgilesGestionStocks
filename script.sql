DROP DATABASE IF EXISTS stocks;
CREATE DATABASE stocks;
USE stocks;

CREATE TABLE IF NOT EXISTS ARTICLE (
	Reference int(15) NOT NULL AUTO_INCREMENT,
	Designation varchar(20) NOT NULL,
	PrixUnitaireHT double NOT NULL,
	Stock int(10) NOT NULL,
	PRIMARY KEY (Reference)
);

CREATE TABLE IF NOT EXISTS FOURNISSEUR (
	Siret varchar(15) NOT NULL,
	Adresse varchar(60) NOT NULL,
	Nom varchar(30) NOT NULL,
	PRIMARY KEY (Siret)
);

CREATE TABLE IF NOT EXISTS FOURNI_ARTICLE (
	Reference int(15) NOT NULL,
	Siret varchar(15) NOT NULL ,
	PrixUnitaireHT double NOT NULL,
	PRIMARY KEY (Siret, Reference)
);

CREATE TABLE IF NOT EXISTS CLIENT (
	Identifiant int(15) NOT NULL AUTO_INCREMENT,
	Nom varchar(30) NOT NULL,
	Prenom varchar(30) NOT NULL,
	Adresse varchar(60),
	Telephone varchar(10),
	Email varchar(60),
	PRIMARY KEY (Identifiant)
);

CREATE TABLE IF NOT EXISTS COMMANDE (
	Identifiant int(15) NOT NULL AUTO_INCREMENT,
	Client int(15) NOT NULL,
	DateCommande DATETIME NOT NULL,
	PRIMARY KEY (Identifiant, Client)
);

CREATE TABLE IF NOT EXISTS INCLU_ARTICLE (
	Commande int(15) NOT NULL,
	Article int(15) NOT NULL,
	Quantite int(15) NOT NULL,
	PRIMARY KEY (Commande, Article)
);

CREATE TABLE IF NOT EXISTS UTILISATEUR (
	Login CHAR(30) NOT NULL,
	Mdp CHAR(30) NOT NULL,
	PRIMARY KEY (Login) 
 );
 
ALTER TABLE FOURNI_ARTICLE
ADD FOREIGN KEY FK_1_FOURNI_ARTICLE (Reference) REFERENCES ARTICLE (Reference),
ADD FOREIGN KEY FK_2_FOURNI_ARTICLE (Siret) REFERENCES FOURNISSEUR (Siret);

ALTER TABLE COMMANDE
ADD FOREIGN KEY FK_1_COMMANDE (Client) REFERENCES CLIENT (Identifiant);
 
ALTER TABLE INCLU_ARTICLE
ADD FOREIGN KEY FK_1_INCLU_ARTICLE (Commande) REFERENCES COMMANDE (Identifiant),
ADD FOREIGN KEY FK_2_INCLU_ARTICLE (Article) REFERENCES ARTICLE (Reference);


--
-- Remplissage des tables
--

INSERT INTO `article` (`Reference`, `Designation`, `PrixUnitaireHT`, `Stock`) VALUES
(1, 'Article1', 50, 20),
(2, 'Article2', 100, 5),
(3, 'Chaise', 199, 3),
(4, 'Tour Eiffel', 789613, 1),
(5, 'Pain', 2, 120);

INSERT INTO `client` (`Identifiant`, `Nom`, `Prenom`, `Adresse`, `Telephone`, `Email`) VALUES
(1, 'Ducroz', 'Jules', '10 Avenue XXX', '0384123456', 'jules.ducroz@uha.fr'),
(2, 'Couchot', 'Adrien', '3 Rue ZZZ', '0666666666', 'adrien.couchot@uha.fr'),
(3, 'Hammoudi', 'Karim', '28 Boulevard AAA', '0389987654', 'karim.hammoudi@uha.fr');


INSERT INTO `fournisseur` (`Siret`, `Adresse`, `Nom`) VALUES
("12345678901234", '85 Avenue AAA', 'FournisseurA'),
("51947352782728", '63 Rue BBB', 'FournisseurB'),
("78913528728286", '34 Rue CCC', 'FournisseurC');


INSERT INTO `utilisateur` (`Login`, `Mdp`) VALUES
('admin', 'admin'),
('utilisateur', 'utilisateur');


INSERT INTO `commande` (`Identifiant`, `Client`, `DateCommande`) VALUES
(1, 1, '2018-03-01 09:20:08'),
(2, 3, '2018-02-14 15:03:59');


INSERT INTO `inclu_article` (`Commande`, `Article`, `Quantite`) VALUES
(1, 1, 15),
(1, 4, 1),
(1, 5, 20),
(2, 2, 4),
(2, 3, 2);


INSERT INTO `fourni_article` (`Reference`, `Siret`, `PrixUnitaireHT`) VALUES
(1, "12345678901234", 25),
(1, "51947352782728", 23),
(2, "12345678901234", 1),
(3, "51947352782728", 140.5),
(3, "78913528728286", 150.9),
(4, "78913528728286", 10),
(5, "78913528728286", 0.5);

