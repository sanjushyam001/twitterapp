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

        return userService.findUser(id);
    }

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return userService.savedUser(user);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userService.modifyUser(user);
        return updatedUser;
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {

        Integer status = userService.removeUser(id);


        return status != 0 ? "User with this id "+id+" has been deleted successfully!" :
                "Sorry, given User with this id "+id+" is not available !";


    }



}
