-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into default_account (salutation, email, firstname, lastname, password, phonenumber, role, registration, street, zipcode, city, houseNr, registrationtyp) values('Herr', 'goldi23@freenet.de', 'andy', 'goldi', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city', '8', 'DEFAULTACCOUNT');

--Februar
<<<<<<< 2fb906d30e7a349cefb17830d87a9742808ffe9e
--insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate) values('VexFeb2017', 'VEX IQ Programmierung', 'lex.jpg', FILE_READ('classpath:vex-kurs-beschreibung.dat'), 'Beginner', 444.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', TO_TIMESTAMP('2017-02-18 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));
--insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate) values ('LegoFeb2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 420.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', TO_TIMESTAMP('2017-02-18 13:30:00', 'YYYY-MM-DD HH24:MI:SS'));

--Maerz
--insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate) values ('LegoMar2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 420.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-mar-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', TO_TIMESTAMP('2017-03-03 10:10:10', 'YYYY-MM-DD HH24:MI:SS'));

--Mai
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate, timeofday) values ('LegoEinsMai2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 210.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-mar-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', TO_TIMESTAMP('2017-05-05 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), '17:00 - 18:30 Uhr');
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate, timeofday) values ('LegoZweiMai2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 210.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', TO_TIMESTAMP('2017-05-06 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), '17:00 - 18:30 Uhr');
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate, timeofday) values('VexMai2017', 'VEX IQ Programmierung', 'lex.jpg', FILE_READ('classpath:vex-kurs-beschreibung.dat'), 'Beginner', 190.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', TO_TIMESTAMP('2017-05-06 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), '17:00 - 18:30 Uhr');

-- VEX-IQ (Beschreibung) Links
--http://education.rec.ri.cmu.edu/vex/iq/
--http://education.rec.ri.cmu.edu/training-vexiq-online/ --> Hier steht viel

-- Lego Education Links
-- http://www.legoengineering.com/online-training-courses/
-- http://education.rec.ri.cmu.edu/training-ev3-onsite/
--http://www.education.rec.ri.cmu.edu/content/lego/ev3/
--http://stemrobotics.cs.pdx.edu/node/2643

