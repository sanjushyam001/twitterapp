package com.twitter.repository;

import com.twitter.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("SELECT p FROM post p WHERE p.user.id=?1")
    List<Post> findPostsByUserId(Integer userId);
}
