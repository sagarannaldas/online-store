package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
