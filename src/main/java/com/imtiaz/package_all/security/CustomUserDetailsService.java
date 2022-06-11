package com.imtiaz.package_all.security;


import com.imtiaz.package_all.EntityModel.User;
import com.imtiaz.package_all.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.getUserByUserName(email);

        if (user == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new CustomUserDetails(user);
    }
}
