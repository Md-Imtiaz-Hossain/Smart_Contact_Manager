package com.imtiaz.package_all.Controller;

import com.imtiaz.package_all.EntityModel.UserEntity;
import com.imtiaz.package_all.Repository.UserRepositories;
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
    private UserRepositories userRepositories;

    @GetMapping("/all")
    public List<UserEntity> allUser(){
        return userRepositories.findAll();
    }


}
