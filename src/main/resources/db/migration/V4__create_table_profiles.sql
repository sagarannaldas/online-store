create table profiles
(
    id             bigint primary key,
    bio            TEXT,
    phone_number   varchar(15),
    date_of_birth  DATE,
    loyalty_points int unsigned default 0,
    user_id        bigint not null,
    foreign key (id) references users (id)
);