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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        User userEntity = userRepo.getUserByUserName(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Username Not Found !!! ");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        return customUserDetails;
    }
}
