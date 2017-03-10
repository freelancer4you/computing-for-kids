-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into default_account (salutation, email, firstname, lastname, password, phonenumber, role, registration, street, zipcode, city, houseNr, registrationtyp) values('Herr', 'goldi23@freenet.de', 'andy', 'goldi', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city', '8', 'DEFAULTACCOUNT');

--Februar
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values('VexFeb2017', 'VEX IQ Programmierung', 'lex.jpg', FILE_READ('classpath:vex-kurs-beschreibung.dat'), 'Beginner', 330.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-feb-appointments', '8 Termine Veranstaltung(en), 16 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2017-03-04 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-05-06 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoFeb2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 330.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-feb-appointments', '8 Termine Veranstaltung(en), 16 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2017-03-04 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-05-06 13:30:00', 'YYYY-MM-DD HH24:MI:SS'));

--Maerz
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoMar2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 330.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-mar-appointments', '8 Termine Veranstaltung(en), 16 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2017-03-10 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-05-12 10:10:10', 'YYYY-MM-DD HH24:MI:SS'));

-- VEX-IQ (Beschreibung) Links
--http://education.rec.ri.cmu.edu/vex/iq/
--http://education.rec.ri.cmu.edu/training-vexiq-online/ --> Hier steht viel

-- Lego Education Links
-- http://www.legoengineering.com/online-training-courses/
-- http://education.rec.ri.cmu.edu/training-ev3-onsite/
--http://www.education.rec.ri.cmu.edu/content/lego/ev3/
--http://stemrobotics.cs.pdx.edu/node/2643

