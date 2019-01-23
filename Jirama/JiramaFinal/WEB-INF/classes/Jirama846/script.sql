create user jirama identified by jirama;
grant create session to jirama;
grant create table to jirama;
grant create sequence to jirama;
grant create view to jirama;
grant unlimited tablespace to jirama;

drop table Abonnes;
drop table offre;
drop table DETAILfacture; 
drop table facture; 
drop table prelevement; 
/* drop table Tarifs;  */
drop view detailPrelev;
drop table Tranche; 
drop table Compteur; 
drop table Clients; 
drop table Categorie; 

--create tables
CREATE TABLE Categorie(
	idCateg VARCHAR2(25),
	nomCateg VARCHAR2(50)
);
CREATE TABLE Clients(
	idclient VARCHAR2(25),
	name VARCHAR2(50),
	idCat VARCHAR2(25)
);
CREATE TABLE Compteur(
	numCompteur VARCHAR2(25),
	idclient VARCHAR2(25),
	nature VARCHAR2(10)
);
CREATE TABLE Tranche(
	idTranche VARCHAR2(25),
	libelle VARCHAR2(25),
	qteLim NUMBER(10,2),
	prixUnitaire NUMBER(8,2),
	categClients VARCHAR2(10),
	nature VARCHAR2(25)
);
CREATE TABLE Prelevement(
	idPrelevement VARCHAR2(25),
	indexPreleve DECIMAL(7, 2),
	numCompteur VARCHAR2(25),
	datePrelevmt date,
	etatPv INT
);
CREATE TABLE Facture(
	idfacture VARCHAR2(25),
	dateFacture date,
	totalConso DECIMAL(8, 2),
	mois VARCHAR2(8),
	annee VARCHAR2(5),
	idclient VARCHAR2(25), 
	idPrelevement VARCHAR2(25),
	total NUMBER(10,2),
	majFacture NUMBER(10,2),
	etatFacture NUMBER(5) --0 à annuler
);
CREATE TABLE FactureAvoir(
	idfactureav VARCHAR(25),
	idfacture VARCHAR(25),
	idclient VARCHAR(25),
	dateFacture DATE,
	dateAvoir DATE,
	sommeMoins NUMBER(10,2)
);
CREATE TABLE PvAnnules(
	idPvAn VARCHAR(25),
	idPv VARCHAR(30),
	numCompteur VARCHAR(30),
	indexPreleve NUMBER(10,2),
	datePrelevmt DATE
);
CREATE TABLE DetailFacture(
	idFactDet VARCHAR2(25),
	idFact VARCHAR2(25),
	nom VARCHAR2(30),
	conso NUMBER(10,2),
	PU NUMBER(8,2),
	major NUMBER(8,2),
	sousTotal NUMBER(10,2)
);
--Primary keys
ALTER TABLE Categorie
	ADD CONSTRAINT cat_pk
	PRIMARY KEY(idCateg);
	
ALTER TABLE Clients
	ADD CONSTRAINT cl_pk
	PRIMARY KEY(idclient);

ALTER TABLE Compteur
	ADD CONSTRAINT cpt_pk
	PRIMARY KEY(numCompteur);
	
ALTER TABLE Tranche
	ADD CONSTRAINT tranche_pk
	PRIMARY KEY(idTranche);
	
ALTER TABLE Prelevement
	ADD CONSTRAINT preleve_pk
	PRIMARY KEY(idPrelevement);
	
ALTER TABLE Facture
	ADD CONSTRAINT fact_pk
	PRIMARY KEY(idfacture);
	
CREATE TABLE Offre(
	idOffre VARCHAR2(25) PRIMARY KEY,
	nature VARCHAR2(25), --elect ou eau
	qte NUMBER(10,2), --ex: 15kwh
	cout NUMBER(15,2), --prix correspondant à la qté
	duree NUMBER(10), --en jours
	majoration NUMBER(15,2) --pourcentage en plus si conso>qte
);
CREATE TABLE Abonnes(
	idAbonnes VARCHAR2(25) PRIMARY KEY,
	choix VARCHAR2(25),
	compteur VARCHAR2(25),
	debut DATE,
	fin DATE,
	qteAchete NUMBER(10,2),
	qteCourante NUMBER(10,2),
	prixAchat NUMBER(10,5),
	taux NUMBER(10,2),
	etat NUMBER(8), --1:valide, 11:invalide
	FOREIGN KEY(choix) REFERENCES Offre(idOffre),
	FOREIGN KEY(compteur) REFERENCES Compteur(numCompteur)
);

--Foreign keys
	--Categorie to Clients
ALTER TABLE Clients
	ADD CONSTRAINT Categories
	FOREIGN KEY (idCat)
	REFERENCES Categorie(idCateg);
	-- Categorie to Tranche
ALTER TABLE Tranche
	ADD CONSTRAINT Categorie
	FOREIGN KEY (categClients)
	REFERENCES Categorie(idCateg);
	
	--Clients to Compteur
ALTER TABLE Compteur
	ADD CONSTRAINT Client
	FOREIGN KEY (idclient)
	REFERENCES Clients(idclient);
	
	--Compteur to Prelevement
ALTER TABLE Prelevement
	ADD CONSTRAINT Compteur
	FOREIGN KEY (numCompteur)
	REFERENCES Compteur(numCompteur);
	
	--Prelevement to Facture
ALTER TABLE FACTURE 
	ADD CONSTRAINT Prelevement
	FOREIGN KEY(idPrelevement)
	REFERENCES Prelevement(idPrelevement);
	
-- ALTER TABLE Prelevement
	-- ADD CONSTRAINT Clients
	-- FOREIGN KEY (idclient)
	-- REFERENCES Clients(idclient);
	
ALTER TABLE DetailFacture
	ADD CONSTRAINT detfact_pk
	PRIMARY KEY(idFactDet);
	
ALTER TABLE DetailFacture
	ADD CONSTRAINT Fact
	FOREIGN KEY (idFact)
	REFERENCES Facture(idfacture);
	
--quelques donnees
INSERT INTO Categorie VALUES('Ca1','Particulier');

INSERT INTO Clients VALUES('Cl1', 'Randria','Ca1');
INSERT INTO Clients VALUES('Cl2', 'Rasoa','Ca1');
INSERT INTO Clients VALUES('Cl3', 'Rabe','Ca1');
-- INSERT INTO Clients VALUES('Cl4', 'Rakoto','Ca1');
-- INSERT INTO Clients VALUES('Cl5', 'Razafy','Ca1');
-- INSERT INTO Clients VALUES('Cl6', 'Miller','Ca1');
-- INSERT INTO Clients VALUES('Cl7', 'Randria','Ca1');
-- INSERT INTO Clients VALUES('Cl8', 'Rajao','Ca1');
-- INSERT INTO Clients VALUES('Cl9', 'Scott','Ca1');
-- INSERT INTO Clients VALUES('Cl10', 'Andria','Ca1');
-- INSERT INTO Clients VALUES(1146, 'Razafy', 'Mitsilo', 'Tanjombato');
-- INSERT INTO Clients VALUES(1133, 'Bekoto', 'Tsiry', 'Malaza');
-- INSERT INTO Clients VALUES(1289, 'Rajao', 'Marie', 'Iavoloha');
-- INSERT INTO Clients VALUES(1230, 'Rakoto', 'Maurice', 'Ambaniatsimo');
-- INSERT INTO Clients VALUES(1202, 'Rakoto', 'Zafy', 'And/fotsy');
-- INSERT INTO Clients VALUES(1107, 'Razafy', 'Koto', 'Tanjombato');

INSERT INTO Compteur VALUES('Cp1','Cl1','elect');
INSERT INTO Compteur VALUES('Cp2','Cl2','eau');
INSERT INTO Compteur VALUES('Cp3','Cl3','elect');
INSERT INTO Compteur VALUES('Cp4','Cl1','elect');
INSERT INTO Compteur VALUES('Cp5','Cl2','elect');
-- INSERT INTO Compteur VALUES('Cp6','Cl4','elect');
-- INSERT INTO Compteur VALUES('Cp7','Cl5','elect');
-- INSERT INTO Compteur VALUES('Cp8','Cl6','elect');
-- INSERT INTO Compteur VALUES('Cp9','Cl7','elect');
-- INSERT INTO Compteur VALUES('Cp10','Cl8','elect');
-- INSERT INTO Compteur VALUES('Cp11','Cl9','elect');
-- INSERT INTO Compteur VALUES('Cp12','Cl10','elect');
-- INSERT INTO Compteur VALUES('Cp13','Cl3','elect');
INSERT INTO Compteur VALUES('Cp14','Cl1','elect');

INSERT INTO Tranche VALUES('T1','tranche 1 elec',1000,120,'Ca1','elect');
INSERT INTO Tranche VALUES('T3','tranche 2 elec',500,150,'Ca1','elect');
INSERT INTO Tranche VALUES('T2','tranche 3 elec',-1,180,'Ca1','elect');
INSERT INTO Tranche VALUES('T4','tranche  eau',-1,90,'Ca1','eau');

alter session set nls_date_format = 'YYYY-MM-DD';

INSERT INTO Prelevement VALUES('Pv1',0,'Cp1','2018-05-07',21);
INSERT INTO Prelevement VALUES('Pv2',100,'Cp1','2018-06-07',1);
INSERT INTO Prelevement VALUES('Pv3',0, 'Cp2','2018-05-14',21);
INSERT INTO Prelevement VALUES('Pv4',100,'Cp2','2018-06-14',1);
INSERT INTO Prelevement VALUES('Pv5',0,'Cp4','2018-05-04',21);
INSERT INTO Prelevement VALUES('Pv6',300,'Cp4','2018-06-04',1);
INSERT INTO Prelevement VALUES('Pv7',0,'Cp5','2018-05-10',21);
INSERT INTO Prelevement VALUES('Pv8',400,'Cp5','2018-06-10',1);
INSERT INTO Prelevement VALUES('Pv9',0,'Cp14','2018-05-10',21);
INSERT INTO Prelevement VALUES('Pv10',356,'Cp14','2018-06-10',1);
INSERT INTO Prelevement VALUES('Pv11',0,'Cp3','2018-05-07',21);
INSERT INTO Prelevement VALUES('Pv12',80,'Cp3','2018-06-07',1);

INSERT INTO Offre VALUES('O1', 'eau', 20, 1500, 30, 1.2);
INSERT INTO Offre VALUES('O2', 'elect', 20, 2500, 30, 1.2);

alter session set nls_date_format = 'DD-MM-YYYY';
INSERT INTO Abonnes VALUES('A1', 'O1', 'Cp2', '01-01-2019', '01-02-2019', 20, 20, 1500, 1.5, 1);
INSERT INTO Abonnes VALUES('A2', 'O2', 'Cp14', '01-01-2019', '01-02-2019', 20, 20, 2500, 1.5, 1);

-- drop sequence idpv;
-- drop sequence idFact;
-- drop sequence idfactDet;
-- drop sequence idCp;
-- drop sequence factDet;
-- drop sequence idcat;
-- drop sequence idoffre;
-- drop sequence idabonnes;

CREATE SEQUENCE idoffre start with 3;
CREATE SEQUENCE idabonnes start with 3;

CREATE SEQUENCE idpv START WITH 9;
select idpv.NEXTVAL FROM DUAL;

CREATE SEQUENCE idFact;
select idFact.NEXTVAL FROM DUAL;

CREATE SEQUENCE idCp START WITH 14;
select idCp.NEXTVAL FROM DUAL;

CREATE SEQUENCE factDet;
SELECT factDet.NEXTVAL FROM DUAL;

CREATE SEQUENCE idcat;
SELECT idcat.NEXTVAL FROM DUAL;

CREATE SEQUENCE idtr;
SELECT idtr.NEXTVAL FROM DUAL;

CREATE SEQUENCE idClient;

CREATE SEQUENCE idAvoir;
CREATE SEQUENCE idAnnules;
-- create view etatFacture as (select Idfacture,idTarif,facture.idPrelevement,facture.numCompteur,conso1,conso2,datePrelevmt as dateFacture,dureeConso from facture,prelevement where facture.idPrelevement = prelevement.idPrelevement);

create view DetailPrelev as(select idPrelevement,indexPreleve,datePrelevmt,prelevement.numCompteur,nature,compteur.idclient,etatPv from prelevement,compteur where prelevement.numCompteur = compteur.numCompteur);

commit;