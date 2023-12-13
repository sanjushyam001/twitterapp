package com.twitter.service;

import com.twitter.models.User;

import java.util.List;

public interface IUserService {
    public User savedUser(User user);
    public List<User> findAllUsers();
    public User findUser(Integer id);
    public User modifyUser(User user);
    public Integer removeUser(Integer id);
}
