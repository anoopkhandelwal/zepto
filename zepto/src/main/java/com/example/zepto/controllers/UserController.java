package com.example.zepto.controllers;

import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.User;
import com.example.zepto.pojos.AddUserRequest;
import com.example.zepto.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public User getUser(@RequestParam(value = "userId") Integer userId) throws NotFoundException {

        return userService.getUser(userId);

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User createUser(@RequestBody AddUserRequest request) {
        return userService.createUser(request);
    }
}
