--insert into role_tbl(id,role) values (1,'ROLE_ADMIN');
--insert into role_tbl(id,role) values (2,'ROLE_FACULTY');
--insert into role_tbl(id,role) values (3,'ROLE_STUDENT');

-- Sample Credential/ username: phil password: super
-- Has All Roles
insert into credential(id,username,password) values (1,'phil','$2a$10$r8XxEMr5VT01bp7R3uVziOPdH6XLY7l4wvRdr2URERD4g8qS/kLjy');
insert into role_tbl(id,role,role_id) values (1,'ROLE_ADMIN',1);
--insert into role_tbl(id,role,role_id) values (2,'ROLE_FACULTY',1);
--insert into role_tbl(id,role,role_id) values (3,'ROLE_STUDENT',1);

-- Has Student Roles
insert into credential(id,username,password) values (2,'jim','$2a$10$r8XxEMr5VT01bp7R3uVziOPdH6XLY7l4wvRdr2URERD4g8qS/kLjy');
insert into role_tbl(id,role,role_id) values (4,'ROLE_STUDENT',2);

-- Has Faculty
insert into credential(id,username,password) values (3,'ankhya','$2a$10$r8XxEMr5VT01bp7R3uVziOPdH6XLY7l4wvRdr2URERD4g8qS/kLjy');
insert into role_tbl(id,role,role_id) values (5,'ROLE_FACULTY',3);

-- Populate Faculty
insert into faculty(id,name,title) values (1,'Computer Science Faculty','Compro');
insert into faculty(id,name,title) values (2,'MBA','MBA Students');

-- Students
insert into student (id,student_id,name,email) values (1,980,'Jim','jim@miu.edu');

-- Entry
insert into entry (id,enrol_start_date,has_write_access,name,start_date) values (1,'2019-10-07',1,'August Entry','2019-10-07');


-- Entry Student
insert into entry_student_list (entry_id,student_list_id) values (1,1);
