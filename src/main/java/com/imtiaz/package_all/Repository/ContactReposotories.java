package com.imtiaz.package_all.Repository;

import com.imtiaz.package_all.EntityModel.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactReposotories extends JpaRepository<ContactEntity, Long> {

    // pagination method
    @Query("from ContactEntity as c where c.user.id =:userId")
    //currentPage-page
    //Contact Per page - 5
    public Page<ContactEntity> findContactsByUser(@Param("userId") Long userId, Pageable pePageable);

}
