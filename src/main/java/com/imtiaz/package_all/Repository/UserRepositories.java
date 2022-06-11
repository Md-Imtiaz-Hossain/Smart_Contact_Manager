package com.imtiaz.package_all.Repository;

import com.imtiaz.package_all.EntityModel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepositories extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.email = :email")
    public UserEntity getUserByUserName(@Param("email") String email);
}
