create table Evaluation
(
    id               bigint auto_increment
        primary key,
    student_id       bigint not null,
    internship_id    bigint       not null,
    technical_skills varchar(200) not null,
    communication    varchar(100) not null,
    teamwork         varchar(200) not null,
    responsibility   varchar(100) not null,
    overall_rating   int          not null,
    comment          varchar(255) not null,
    created_at       datetime     not null,
    constraint Evaluation_internship_id_fk
        foreign key (internship_id) references internship (id),
    constraint Evaluation_user_id_fk
        foreign key (student_id) references user (id)
);

