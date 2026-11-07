create table Interview
(
    id             bigint auto_increment
        primary key,
    application_id bigint       not null,
    date           date         not null,
    time           time         not null,
    type           varchar(50)  not null,
    meeting_link   varchar(100) not null,
    location       varchar(255) not null,
    status         varchar(100) not null,
    constraint Interview_application_id_fk
        foreign key (application_id) references application (id)
);

