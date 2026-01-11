-- create the wishlist join table
create table wishlist
(
    user_id    bigint not null,
    product_id bigint not null,
    primary key (user_id, product_id),
    foreign key (user_id) references users (id),
    foreign key (product_id) references products (id)
);