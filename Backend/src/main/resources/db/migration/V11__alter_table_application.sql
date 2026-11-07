alter table application
    change cv cv_id bigint not null;

alter table application
    add constraint application_cv_id_fk
        foreign key (cv_id) references cv (id);

