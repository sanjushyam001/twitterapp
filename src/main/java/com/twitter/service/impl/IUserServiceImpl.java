package com.twitter.service.impl;

import com.twitter.models.User;
import com.twitter.repository.UserRepository;
import com.twitter.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImpl implements IUserService {

    private UserRepository userRepository;

    public IUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) {

        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new RuntimeException("User is not found with this id "+id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) {
        if(userId1==userId2){
            throw new RuntimeException("User cant follow itself");
        }
        User user1=findUserById(userId1);
        User user2=findUserById(userId2);
        List<Integer> followers = user2.getFollowers();
        if(followers==null){
            followers=new ArrayList<>();
        }
        followers.add(user1.getId());
        List<Integer> followings=user1.getFollowings();
        if(followings==null){
            followings=new ArrayList<>();
        }
        followings.add(user2.getId());
        user1.setFollowings(followings);
        user2.setFollowers(followers);
        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if(optional.isPresent()){
           User u = optional.get();
           User modifiedUser=u.builder()
                   .id(user.getId())
                   .firstName(user.getFirstName())
                   .lastName(user.getLastName())
                   .email(user.getEmail())
                   .gender(user.getGender())
                   .build();
           return userRepository.save(modifiedUser);
        }
        throw new RuntimeException("User is not found with this id"+user.getId());
    }

    @Override
    public List<User> searchUserByQuery(String query) {

        return userRepository.searchUserByQuery(query);
    }
}
