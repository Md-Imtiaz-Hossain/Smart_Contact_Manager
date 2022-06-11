package com.imtiaz.package_all.Service;

import com.imtiaz.package_all.EntityModel.User;
import com.imtiaz.package_all.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);

    }


}
