create table course_participants (course varchar(255) not null, usermail varchar(255) not null, primary key (course, usermail)); 
create table courses (id varchar(255) not null, description clob not null, appointments varchar(255), curriculum varchar(255) not null, duration varchar(255), icon varchar(255) not null, level varchar(255) not null, name varchar(255) not null, place clob not null, price double not null, requirements clob, begindate timestamp not null, enddate timestamp not null, primary key (id)); 
create table default_account (email varchar(255) not null, registration timestamp not null, registrationtyp varchar(15) not null, role varchar(255) not null, city varchar(40) not null, housenr varchar(4) not null, street varchar(82) not null, zipcode varchar(10) not null, childage varchar(255), childname varchar(255), firstname varchar(81), lastname varchar(81) not null, password varchar(255) not null, phonenumber varchar(255) not null, salutation varchar(255) not null, title varchar(255), primary key (email)); 
create table google_account (email varchar(255) not null, registration timestamp not null, registrationtyp varchar(15) not null, role varchar(255) not null, display_name varchar(255) not null, familyname varchar(81) not null, gender varchar(255) not null, givenname varchar(81), image_url varchar(255) not null, language varchar(255) not null, primary key (email));  
alter table default_account add constraint UK_EMAIL_DEFAULT_ACCOUNT unique (email); 
alter table google_account add constraint UK_EMAIL_GOOGLE_ACCOUNT unique (email);
alter table course_participants add constraint fk_cp_course_id foreign key (course) references courses; 
alter table course_participants add constraint fk_cp_defaultaccount_id foreign key (usermail) references default_account;
alter table course_participants add constraint fk_cp_googleaccount_id foreign key (usermail) references google_account;

-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into default_account (salutation, email, firstname, lastname, password, phonenumber, role, registration, street, zipcode, city, houseNr, registrationtyp) values('Herr', 'goldi23@freenet.de', 'andy', 'goldi', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city', '8', 'DEFAULTACCOUNT');

--Februar
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values('VexFeb2017', 'VEX IQ Programmierung', 'lex.jpg', FILE_READ('classpath:vex-kurs-beschreibung.dat'), 'Beginner', 444.00, 'Chopinstraße 9A, 04103 Leipzig', 'vex-curriculum', 'vex-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2017-02-18 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-04-22 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoFeb2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 420.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-feb-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2017-02-18 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-04-22 13:30:00', 'YYYY-MM-DD HH24:MI:SS'));

--Maerz
insert into courses(id, name, icon, description, level, price, place, curriculum, appointments, duration, requirements, begindate, enddate) values ('LegoMar2017', 'Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 420.00, 'Chopinstraße 9A, 04103 Leipzig', 'lego-curriculum', 'lego-mar-appointments', '10 Termine Veranstaltung(en), 20 Unterrichtseinheit(en)', 'Eigener Laptop', TO_TIMESTAMP('2017-03-03 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'));

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

