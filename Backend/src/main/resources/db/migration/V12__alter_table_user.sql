alter table user
drop column first_name;

alter table user
drop column last_name;

alter table user
drop column phone;

create table student
(
    id          bigint auto_increment
        primary key,
    user_id     bigint       not null,
    first_name  varchar(50)  not null,
    last_name   varchar(55)  not null,
    phone       varchar(20)  not null,
    university  varchar(200) not null,
    faculty     varchar(100) not null,
    study_year  int          not null,
    city        varchar(200) not null,
    description varchar(255) not null,
    constraint student_user_id_fk
        foreign key (user_id) references user (id)
);

create table company
(
    id           bigint auto_increment
        primary key,
    user_id      bigint       not null,
    company_name varchar(250) not null,
    description  varchar(200) not null,
    website      varchar(200) not null,
    phone        varchar(20)  not null,
    address      varchar(200) not null,
    city         varchar(200) not null,
    industry     varchar(100) null,
    constraint company_user_id_fk
        foreign key (user_id) references user (id)
);




