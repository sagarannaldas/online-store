package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.dtos.UserSummary;
import com.sagarannaldas.online_store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query(value = "select * from users", nativeQuery = true)
    List<User> findAllWithAddress();

    @Query("select u.id as id, u.email as email from User u where u.profile.loyaltyPoints > :loyaltyPoints order by u.email")
     List<UserSummary> findLoyalUsers(@Param("loyaltyPoints") int loyaltyPoints);
}
