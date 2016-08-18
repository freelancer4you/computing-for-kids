-- PW=Blade23 yyyy-MM-dd hh:mm:ss
insert into users (email,firstname,lastname, username, password, phonenumber, role, registration, street, zipcode, city) values('goldi23@freenet.de', 'goldi', 'goldi', 'username', '$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.', 'phone', 'ADMIN', CURRENT_TIMESTAMP, 'street', 'plz', 'city');

--insert into courses(name, icon, description, level, price) values ('3D Programming for Kids', 'threejs.png', 'The C programming language is one of the most popular and widely used programming languages and is commonly used to programme operating systems such as Unix. It is a general-purpose programming langu.', 'Intermediate', 2334.99);
--insert into courses(name, icon, description, level, price) values ('HTML for Kids', 'html.png', 'If you have studied the Introduction to Programming in C course available on ALISON it is now time to further enhance your C programming skills by studying numbers, variables and arrays and the funct..', 'Beginner', 239.99);
--insert into courses(name, icon, description, level, price) values ('CSS for Kids', 'css.jpg', 'In computer science control flow is a very important concept to understand for the development of effective and efficient software programs. Control flow is the order in which individual statements, ..', 'Intermediate', 2595.99);
--insert into courses(name, icon, description, level, price) values ('Javascript for Kids', 'javascript.jpg', 'Many of todays Web 2.0 applications rely on JavaScript to create rich and dynamic user interfaces. In this course students will learn the fundamentals of the JavaScript programming language along with the Document Object Model, the key component to producing interactive web pages.', 'All', 2595.99);
--insert into courses(name, icon, description, level, price) values ('Scratch', 'scratch.png', 'In this course, young students enter the world of computer science by learning how to create animations, computer games, and interactive projects. Using Scratch, a graphical programming language developed at MIT, students learn fundamental programming concepts such as variables, loops, conditional statements, and event handling. As they teach a mischievous cat to dance, explore a maze, or play games, students learn how to use math and computer code to think creatively. The course will show students how to make and import objects, create audio recordings, and use them to develop interactive projects. At the end of the course, students create their own computer game and share it with their CTYOnline instructor and classmates.', 'Beginner', 2595.99);

insert into courses(name, icon, description, level, price, place, curriculum, appointments) values ('Lego Programmierung', 'ev3.png', FILE_READ('classpath:lego-kurs-beschreibung.dat'), 'Beginner', 540.99, 'Volkshochschule, Löhrstraße 3 - 7', 'lego-curriculum', 'lego-appointments');
insert into courses(name, icon, description, level, price, place, curriculum, appointments) values ('Javascript for Kids', 'javascript.jpg', FILE_READ('classpath:javascript-kurs-beschreibung.dat'), 'Beginner', 495.99, 'Volkshochschule, Löhrstraße 3 - 7', 'vex-curriculum', 'vex-appointments');
insert into courses(name, icon, description, level, price, place, curriculum, appointments) values('VEX IQ Programmierung', 'lex.jpg', FILE_READ('classpath:vex-kurs-beschreibung.dat'), 'Beginner', 540.99, 'Volkshochschule, Löhrstraße 3 - 7', 'javascript-curriculum', 'javascript-appointments');

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
insert into schedules (begindate, enddate, course_name) values(TO_TIMESTAMP('2016-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'VEX IQ Programmierung');
insert into schedules (begindate, enddate, course_name) values(TO_TIMESTAMP('2016-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Lego Programmierung');
insert into schedules (begindate, enddate, course_name) values(TO_TIMESTAMP('2016-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-31 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'Javascript for Kids');