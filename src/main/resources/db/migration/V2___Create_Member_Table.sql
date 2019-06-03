CREATE TABLE member
(
    id         SERIAL primary key,
    first_name varchar(200) NOT NULL,
    last_name  varchar(200) NOT NULL,
    birth_date date         not null,
    plan_id    integer
);

ALTER TABLE member
ADD foreign key (plan_id) REFERENCES plan (id);