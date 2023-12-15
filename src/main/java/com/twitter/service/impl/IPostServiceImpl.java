package com.twitter.service.impl;

import com.twitter.models.Post;
import com.twitter.models.User;
import com.twitter.repository.PostRepository;
import com.twitter.repository.UserRepository;
import com.twitter.service.IPostService;
import com.twitter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IPostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;
    @Override
    public Post createNewPost(Post post, Integer userId) {

        User user=userService.findUserById(userId);
        if(user==null) throw new RuntimeException("User not found with this id "+userId);
        Post p = Post.builder().caption(post.getCaption())
                .imageUrl(post.getImageUrl())
                .videoUrl(post.getImageUrl())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        Post savedPost = postRepository.save(p);
        return savedPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        User user=userService.findUserById(userId);
        Post post = findPostById(postId);
        if(user.getId()!=post.getUser().getId()){
            throw new RuntimeException("You can't delete this post");
        }
         postRepository.delete(post);
        return "Post successfully deleted !";
    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) {
        return postRepository.findPostsByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> findAllPosts() {
        return null;
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) {
        User user=userService.findUserById(userId);
        Post post = findPostById(postId);
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else{
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likedPost(Integer postId, Integer userId) {
        User user=userService.findUserById(userId);
        Post post = findPostById(postId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else{
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
