create table Notification
(
    id         bigint auto_increment
        primary key,
    user_id    bigint       not null,
    title      varchar(255) not null,
    message    varchar(255) not null,
    created_at datetime     not null,
    constraint Notification_user_id_fk
        foreign key (user_id) references user (id)
);

