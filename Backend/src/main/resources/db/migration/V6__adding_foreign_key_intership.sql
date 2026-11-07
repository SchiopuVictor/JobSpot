alter table internship
    add constraint internship_user_id_fk
        foreign key (company_id) references user (id);

