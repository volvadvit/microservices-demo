create table if not exists idempotency_key
(
    key         varchar(128) not null primary key,
    response    varchar(255) not null,
    expiry_date timestamp
);