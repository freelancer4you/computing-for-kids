-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into users (salutation, email,firstname,lastname, username, password, phonenumber, role, registration, street, zipcode, city, houseNr) values('Herr', 'goldi23@freenet.de', 'goldi', 'goldi', 'username', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city', '8');

insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoOct2016', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 540.99, 'Volkshochschule, Löhrstraße 3 - 7', 'lego-curriculum', 'lego-appointments', '18 Termine Veranstaltung(en), 54 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2016-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'));
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('JSOct2016', 'Javascript for Kids', 'javascript.jpg', FILE_READ('classpath:javascript-kurs-beschreibung.dat'), 'Beginner', 495.99, 'Volkshochschule, Löhrstraße 3 - 7', 'vex-curriculum', 'vex-appointments', '18 Termine Veranstaltung(en), 54 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2016-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'));
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values('VexOct2016', 'VEX IQ Programmierung', 'lex.jpg', FILE_READ('classpath:vex-kurs-beschreibung.dat'), 'Beginner', 540.99, 'Volkshochschule, Löhrstraße 3 - 7', 'javascript-curriculum', 'javascript-appointments', '18 Termine Veranstaltung(en), 54 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2016-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'));

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

