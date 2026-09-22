create table CV
(
    id          bigint auto_increment
        primary key,
    student_id  bigint       not null,
    file_name   varchar(255) not null,
    uploaded_at datetime     not null,
    constraint CV_user_id_fk
        foreign key (student_id) references user (id)
);

