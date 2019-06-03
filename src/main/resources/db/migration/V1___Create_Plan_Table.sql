CREATE TABLE plan
(
    id         SERIAL PRIMARY KEY ,
    name       varchar(200) NOT NULL,
    limited    boolean default false,
    start_date date,
    end_date   date
);