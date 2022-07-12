drop database if exists ijse;
create database if not exists ijse;
show databases;
use ijse;

drop table if exists Student;
create table if not exists Student(
    student_id varchar(45) not null,
    student_name varchar(45) not null default 'Unknown',
    email text,
    contact varchar(20) not null,
    address text not null,
    nic varchar(45) not null,
    constraint primary key (student_id)
    );

drop table if exists intake;
create table if not exists intake(
    intake_id varchar(45),
    subject_name varchar(45),
    intakecol varchar(45),
    description varchar (45),
    course_id varchar(45),
    constraint primary key (intake_id),
    foreign key(course_id) references Course (course_id) on delete cascade on update cascade

);

drop table if exists Registration;
create table if not exists Registration(
    registration_id varchar(45) not null,
    reg_date date,
    student_id varchar(45),
    intake_id varchar (45),
    constraint primary key (registration_id),
    foreign key(student_id) references Student (student_id) on delete cascade on update cascade,
    foreign key (intake_id) references intake (intake_id) on delete cascade on update cascade
    );
