create schema if not exists evg;

create sequence if not exists hibernate_sequence START 2;

create table if not exists evg.user
(
    id             bigint  primary key,
    name           varchar,
    first_name     varchar,
    last_name      varchar,
    email          varchar,
    phone          varchar,
    password       varchar
);

create table if not exists evg.order
(
    id             bigint  primary key,
    USER_ID        BIGINT,
    ORDER_KEY      varchar,
    TOTAL_PRICE    decimal
);

create table if not exists evg.product
(
    id             bigint  primary key,
    NAME           VARCHAR,
    DESCRIPTION    varchar,
    COST           decimal
);

create table if not exists evg.order_compound
(
    PRODUCT_ID     BIGINT,
    ORDER_ID       BIGINT,
    COUNT          numeric
);
