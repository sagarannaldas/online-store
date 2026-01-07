package com.sagarannaldas.online_store.user;



public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
