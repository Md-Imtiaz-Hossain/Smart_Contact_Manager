package com.imtiaz.package_all.Service;

import com.imtiaz.package_all.EntityModel.Contact;
import com.imtiaz.package_all.EntityModel.User;
import com.imtiaz.package_all.Repository.ContactRepo;
import com.imtiaz.package_all.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public void saveContact(Contact contact) {
        contactRepo.save(contact);

    }

//    public Contact getContactByName(String userName) {
//        return  contactRepo.findByName(userName);
//    }



}
