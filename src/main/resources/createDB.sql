CREATE TABLE employee(
    id       int not null auto_increment,
    fisrt_name  varchar,
    last_name    varchar,
    external_employee     boolean,
    age   int,
    sex    char,
    employee_status  varchar,
    country varchar,
    state varchar,
    city varchar,
    street varchar,
    house varchar,
    flat varchar,
    PRIMARY KEY (id)
);

CREATE TABLE emplyee_personal_info(
    id      int not null auto_increment,
    birthday date,
    passport_data    DECIMAL,
    PRIMARY KEY (id)
);

CREATE TABLE project(
    id      int not null auto_increment,
    project_title varchar,
    PRIMARY KEY (id)
);

CREATE TABLE employee_unit(
    id      int not null auto_increment,
    unit_title varchar,
    PRIMARY KEY (id)
);