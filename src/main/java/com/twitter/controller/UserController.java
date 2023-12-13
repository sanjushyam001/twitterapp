package com.twitter.controller;

import com.twitter.models.User;
import com.twitter.service.impl.IUserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    private IUserServiceImpl userService;

    public UserController(IUserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {

        return userService.findUserById(id);
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return updatedUser;
    }

    @PutMapping("/user/follows/{userId1}/{userId2}")
    public User followUserHandler(
            @PathVariable Integer userId1,
            @PathVariable Integer userId2){

        return userService.followUser(userId1,userId2);
    }
    @GetMapping("/user/search")
    public List<User> searchUserByQuery(
           @RequestParam("query") String query
           ){

        return userService.searchUserByQuery(query);
    }

//    @DeleteMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//
//        Integer status = userService.removeUser(id);
//
//
//        return status != 0 ? "User with this id "+id+" has been deleted successfully!" :
//                "Sorry, given User with this id "+id+" is not available !";
//
//
//    }



}
