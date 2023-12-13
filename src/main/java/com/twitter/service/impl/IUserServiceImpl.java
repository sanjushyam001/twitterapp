package com.twitter.service.impl;

import com.twitter.models.User;
import com.twitter.repository.UserRepository;
import com.twitter.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImpl implements IUserService {

    private UserRepository userRepository;

    public IUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User savedUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public User modifyUser(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if(optional.isPresent()){
            User u = optional.get();
            User modifiedUser= u.builder().id(user.getId()).
                    firstName(user.getFirstName()).
                    lastName(user.getLastName()).
                    email(user.getEmail()).
                    password(user.getPassword()).
                    build();
            User savedUser = userRepository.save(modifiedUser);
            return savedUser;

        }
        return null;
    }

    @Override
    public Integer removeUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if(!optional.isPresent())
        return 0;
        else{
            userRepository.deleteById(id);
            return id;
        }

    }
}
