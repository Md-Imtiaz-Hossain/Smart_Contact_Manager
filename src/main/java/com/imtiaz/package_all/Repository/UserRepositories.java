package com.imtiaz.package_all.Repository;
import javax.transaction.Transactional;

import com.imtiaz.package_all.EntityModel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositories extends JpaRepository<UserEntity, Long> {


    @Query("select u from UserEntity u where u.email =:email")
    public UserEntity getUserByUserName(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("delete from UserEntity u where u.email =:email")
    public Integer deleteUserByUserName(@Param("email") String email);


}

