package com.twitter.service;

import com.twitter.models.Post;

import java.util.List;

public interface IPostService {

    Post createNewPost(Post post,Integer userId);

    String deletePost(Integer postId,Integer userId);

    List<Post> findPostsByUserId(Integer userId);

    Post findPostById(Integer postId);

    List<Post> findAllPosts();

    Post savedPost(Integer postId,Integer userId);

    Post likedPost(Integer postId,Integer userId);



}
