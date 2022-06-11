package com.imtiaz.package_all.Controller;

import com.imtiaz.package_all.EntityModel.User;
import com.imtiaz.package_all.Repository.UserRepo;
import com.imtiaz.package_all.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all")
    public List<User> allUser(){
        return userRepo.findAll();
    }


}
