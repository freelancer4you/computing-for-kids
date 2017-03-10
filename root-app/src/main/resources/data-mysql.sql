-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into default_account (salutation, email, firstname, lastname, password, phonenumber, role, registration, street, zipcode, city, houseNr, registrationtyp) values('Herr', 'goldi23@freenet.de', 'andy', 'goldi', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city', '8', 'DEFAULTACCOUNT');

-- TODO: Feld: description fuellen: mit h2 = FILE_READ('classpath:lego-kurs-beschreibung.dat')
--Februar
<<<<<<< 2fb906d30e7a349cefb17830d87a9742808ffe9e
--insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate) values('VexFeb2017', 'VEX IQ Programmierung', 'lex.jpg', 'TODO', 'Beginner', 444.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', '2017-02-18 10:10:10') on duplicate key update id = 'VexFeb2017';
--insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate) values ('LegoFeb2017', 'Lego Programmierung', 'ev3.png', 'TODO', 'Beginner', 420.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', '2017-02-18 10:10:10') on duplicate key update id = 'LegoFeb2017';

--Maerz
--insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate) values ('LegoMar2017', 'Lego Programmierung', 'ev3.png', 'TODO', 'Beginner', 420.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-mar-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', '2017-03-03 10:10:10') on duplicate key update id = 'LegoMar2017';

--Mai
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate, timeofday) values('VexMai2017', 'VEX IQ Programmierung', 'lex.jpg', 'TODO', 'Beginner', 190.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', '2017-05-06 10:10:10', '17:00 - 18:30 Uhr') on duplicate key update id = 'VexMai2017';
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate, timeofday) values ('LegoMaiEins2017', 'Lego Programmierung', 'ev3.png', 'TODO', 'Beginner', 210.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', '2017-05-05 10:10:10', '17:00 - 18:30 Uhr') on duplicate key update id = 'LegoMaiEins2017';
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration_desc, duration_weeks, requirements, begindate, timeofday) values ('LegoMaiZwei2017', 'Lego Programmierung', 'ev3.png', 'TODO', 'Beginner', 210.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-mar-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 16, 'Eigener Laptop', '2017-05-06 10:10:10', '17:00 - 18:30 Uhr') on duplicate key update id = 'LegoMaiZwei2017';


-- VEX-IQ (Beschreibung) Links
--http://education.rec.ri.cmu.edu/vex/iq/
--http://kursevrorubldollar.com/vex-iq-programming-software/
--http://education.rec.ri.cmu.edu/training-vexiq-online/ --> Hier steht viel

-- Lego Education Links
-- http://www.legoengineering.com/online-training-courses/
-- http://education.rec.ri.cmu.edu/training-ev3-onsite/
--http://www.education.rec.ri.cmu.edu/content/lego/ev3/
--http://stemrobotics.cs.pdx.edu/node/2643

--insert into course_participants(course_name, user_email) values('VEX IQ Programmierung', 'goldi23@freenet.de');

