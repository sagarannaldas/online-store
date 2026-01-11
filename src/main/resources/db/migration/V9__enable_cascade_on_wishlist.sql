alter table wishlist
    drop foreign key wishlist_ibfk_2;

alter table wishlist
    add constraint wishlist_ibfk_2
        foreign key (product_id) references products (id)
            on delete cascade;