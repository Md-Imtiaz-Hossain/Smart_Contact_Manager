package com.imtiaz.package_all.Repository;

import com.imtiaz.package_all.EntityModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo  extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    public User getUserByUserName(@Param("email") String email);
}
