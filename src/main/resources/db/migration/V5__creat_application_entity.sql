create table Application
(
    id            bigint auto_increment
        primary key,
    student_id    bigint       not null,
    internship_id bigint       not null,
    cover_letter  varchar(255) not null,
    cv            varchar(200) not null,
    status        varchar(100) not null,
    applied_at    datetime     not null,
    constraint Application_internship_id_fk
        foreign key (internship_id) references internship (id),
    constraint Application_user_id_fk
        foreign key (student_id) references user (id)
);

