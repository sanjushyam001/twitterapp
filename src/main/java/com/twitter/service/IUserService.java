package com.twitter.service;

import com.twitter.models.User;

import java.util.List;

public interface IUserService {
    public User registerUser(User user);
    public User findUserById(Integer id);
    public User findUserByEmail(String email);
    public User followUser(Integer userId1,Integer userId2);
    public List<User> findAllUsers();
    public User updateUser(User user);
    public List<User> searchUserByQuery(String query);
}
