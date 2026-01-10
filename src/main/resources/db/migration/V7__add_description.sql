alter table products
    add `description` text null;


alter table products
    modify `description` text not null;