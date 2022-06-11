package com.imtiaz.package_all.Repository;

import com.imtiaz.package_all.EntityModel.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    // pagination method
    @Query("from Contact as c where c.user.id =:userId")
    //currentPage-page
    //Contact Per page - 5
    public Page<Contact> findContactsByUser(@Param("userId") Long userId, Pageable pePageable);

}
