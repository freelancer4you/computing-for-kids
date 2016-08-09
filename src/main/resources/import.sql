--CREATE TABLE IF NOT EXISTS Users (email varchar(200) NOT NULL, firstName varchar(200) NOT NULL, lastName varchar(200) NOT NULL, PRIMARY KEY (email));
INSERT INTO Users (email, firstname, lastname) VALUES ('1001@freenet.de', '1001', 'lastName1001');
INSERT INTO Users (email, firstname, lastname) VALUES ('1002@freenet.de', '1002', 'lastName1002');