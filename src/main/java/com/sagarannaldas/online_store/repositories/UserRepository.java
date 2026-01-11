package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
