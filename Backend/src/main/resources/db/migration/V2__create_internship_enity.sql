create table Internship
(
    id                   bigint auto_increment
        primary key,
    company_name         varchar(50)   not null,
    title                varchar(50)   not null,
    description          varchar(255)  not null,
    requirements         varchar(255)  not null,
    responsibilities     varchar(255)  not null,
    location             varchar(100)  not null,
    type                 varchar(200)  not null,
    duration             datetime      not null,
    availablePositions   varchar(200)  not null,
    salary               decimal(8, 2) not null,
    application_deadline datetime      not null,
    created_at           datetime      not null,
    status               varchar(50)   not null
);

