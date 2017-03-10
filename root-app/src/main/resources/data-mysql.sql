-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into default_account (salutation, email, firstname, lastname, password, phonenumber, role, registration, street, zipcode, city, houseNr, registrationtyp) values('Herr', 'goldi23@freenet.de', 'andy', 'goldi', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city', '8', 'DEFAULTACCOUNT');

-- TODO: Feld: description fuellen: mit h2 = FILE_READ('classpath:lego-kurs-beschreibung.dat')
--Februar
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values('VexApr2017', 'VEX IQ Programmierung', 'lex.jpg', 'TODO', 'Beginner', 330.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-apr-appointments', '8 Termine Veranstaltung(en), 16 Unterrichtseinheit(en)', 'Eigener Laptop', '2017-04-01 10:10:10', '2017-06-03 10:10:10') on duplicate key update id = 'VexApr2017';
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoApr2017', 'Lego Programmierung', 'ev3.png', 'TODO', 'Beginner', 330.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-apr-appointments', '8 Termine Veranstaltung(en), 16 Unterrichtseinheit(en)', 'Eigener Laptop', '2017-04-01 10:10:10', '2017-06-03 10:10:10') on duplicate key update id = 'LegoApr2017';

--Maerz
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoTwoApr2017', 'Lego Programmierung', 'ev3.png', 'TODO', 'Beginner', 330.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-two-apr-appointments', '8 Termine Veranstaltung(en), 16 Unterrichtseinheit(en)', 'Eigener Laptop', '2017-04-07 10:10:10', '2017-06-09 10:10:10') on duplicate key update id = 'LegoTwoApr2017';


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

