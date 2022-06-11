package com.imtiaz.package_all.security;


import com.imtiaz.package_all.EntityModel.UserEntity;
import com.imtiaz.package_all.Repository.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepositories.getUserByUserName(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Username Not Found !!! ");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        return customUserDetails;
    }
}
