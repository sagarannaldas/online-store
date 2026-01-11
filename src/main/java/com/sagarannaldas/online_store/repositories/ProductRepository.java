package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
