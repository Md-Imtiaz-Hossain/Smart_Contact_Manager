package com.imtiaz.package_all.Controller;

import com.imtiaz.package_all.EntityModel.ContactEntity;
import com.imtiaz.package_all.EntityModel.UserEntity;
import com.imtiaz.package_all.Helper.Message;
import com.imtiaz.package_all.Repository.ContactReposotories;
import com.imtiaz.package_all.Repository.UserRepositories;
import com.imtiaz.package_all.Service.ContactService;
import com.imtiaz.package_all.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepositories userRepository;

    @Autowired
    private ContactReposotories contactRepository;

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @Autowired
    ContactReposotories contactReposotories;


    // Method for adding common data for every handler automatically
    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {
        String userName = principal.getName();
        System.out.println("Username --> " + userName);
        UserEntity userEntity = userRepository.getUserByUserName(userName); // Introduce a local variable ctrl+alt+v
        System.out.println(userEntity);
        model.addAttribute("user", userEntity);
    }


    // Home
    @GetMapping("/index")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "/normal/user-dashboard";
    }


    // add contact form handler
    @GetMapping("/add-contact")
    public String contactAdd(Model model) {
        model.addAttribute("title", "Add Contact Form");
        model.addAttribute("contact", new ContactEntity());
        return "/normal/add-contact-form";
    }

    //processing add contact form
    @PostMapping("/process-add-contact-form")
    public String processContact(
            @ModelAttribute ContactEntity contactEntity,
            @RequestParam("profileImage") MultipartFile file,
            Principal principal,HttpSession session) {

        try {
            String name = principal.getName();
            UserEntity userEntity = this.userRepository.getUserByUserName(name);
            //processing and uploading file..
            if(file.isEmpty())            {
                //if the file is empty then try our message
                System.out.println("File is empty");
            }
            else {
                //file the file to folder and update the name to contact
                contactEntity.setPhone(file.getOriginalFilename());
                File saveFile=new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image is uploaded");
            }
            userEntity.getContactEntities().add(contactEntity);
            contactEntity.setUserEntity(userEntity);
            this.userRepository.save(userEntity);
            System.out.println("DATA "+ contactEntity);
            System.out.println("Added to data base");
            //message success.......
            session.setAttribute("message", new Message("Your contact is added !! Add more..", "success") );
        }catch (Exception e) {
            System.out.println("ERROR "+e.getMessage());
            e.printStackTrace();
            //message error
            session.setAttribute("message", new Message("Some went wrong !! Try again..", "danger") );
        }
        return "normal/add-contact-form";
    }

    //show contacts handler
    //per page = 5[n]
    //current page = 0 [page]
    @GetMapping("/show-all-contact/{page}")
    public String showContacts(@PathVariable("page") Integer page ,Model m,Principal principal) {
        m.addAttribute("title","Show User Contacts");
        //contact ki list ko bhejni hai
        String userName = principal.getName();
        UserEntity userEntity = this.userRepository.getUserByUserName(userName);
        //currentPage-page
        //Contact Per page - 5
        Pageable pageable = PageRequest.of(page, 8);
        Page<ContactEntity> contacts = this.contactRepository.findContactsByUser(userEntity.getId(),pageable);
        m.addAttribute("contacts",contacts);
        m.addAttribute("currentPage",page);
        m.addAttribute("totalPages",contacts.getTotalPages());
        return "normal/show-contacts";
    }


//    // Process the add contact form
//    @PostMapping("/process-add-contact-form")
//    public String processForm(@ModelAttribute Contact contact,
//                              @RequestParam("profileImage") MultipartFile profileImage,
//                              Principal principal,
//                              HttpSession session) {
//
//        String name = principal.getName();
//        System.out.println("Principal Name- " + name);
//        User user = userService.getUserByName(name);
//        System.out.println("User By Principal Name- " + user);
//
//        System.out.println("Contact Name- " + contact);
//
//        contact.setUser(user);
//        contactService.saveContact(contact);


//
//        try {
//
//            String name = principal.getName();
//            System.out.println("Principal Name- " + name);
//            User  user = userService.getUserByName(name);
//            System.out.println("User By Principal Name- " + user);
//
//
//            if(profileImage.isEmpty()){
//                System.out.println("Profile Image is Empty ! ");
//            }else {
//                contact.setImageUrl(profileImage.getOriginalFilename());
//                File saveFile = new ClassPathResource("/static/image").getFile();
//                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + profileImage.getOriginalFilename());
//                Files.copy(profileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("Profile Image is Uploaded.");
//            }
//
//
//        contact.setUser(user);
//        System.out.println("Contact --> " + contact);
//
//            user.getContacts().add(contact);
//            userService.saveUser(user);
//
//            session.setAttribute("message", new Message("Your Contact has been added !! ", "success"));
//
//        }catch (Exception e) {
//            System.out.println("Error --> " + e.getMessage());
//            e.printStackTrace();
//            session.setAttribute("message", new Message("Something went wrong !! ", "danger"));
//
//        }

//        return "/normal/add-contact-form";
//    }


//    // Show Contacts handler
//    @GetMapping("/show-all-contact")
//    public String showContact(Model model, Principal principal) {
//
//        String name = principal.getName();
//        System.out.println("==========Principal Name- " + name);
//        User user = userService.getUserByName(name);
//        System.out.println("===============User By Principal Name- " + user);


//        model.addAttribute("title", "Show all contact");
//        String userName = principal.getName();
//        User user = userService.getUserByName(userName);
//        System.out.println("-----------" + user);
//        System.out.println("-----------" + user.getId());
//
//        List<Contact> contactList = contactRepo.findContactByUser(user.getId());
//
//        model.addAttribute("contacts", contactList);
//
//        System.out.println("===========" + contactList);

//        return "/normal/show-contacts";
//    }


}
