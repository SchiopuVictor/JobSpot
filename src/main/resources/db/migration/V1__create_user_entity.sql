create table User
(
    id            bigint auto_increment
        primary key,
    first_name    varchar(50) not null,
    last_name     varchar(50) not null,
    email         varchar(70) not null,
    password      varchar(50) not null,
    phone         varchar(30) not null,
    role          varchar(20) not null,
    created_at    datetime    not null,
    active        boolean     not null
);

