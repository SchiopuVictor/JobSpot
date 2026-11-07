alter table internship
    change company_name company_id bigint not null;

alter table internship
    change availablePositions available_positions varchar(200) not null;

