package com.twitter.repository;

import com.twitter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE" +
            " u.firstName LIKE %?1% or " +
            "u.lastName LIKE %?1% or " +
            "u.email LIKE %?1%")
    public List<User> searchUserByQuery(String query);
}
