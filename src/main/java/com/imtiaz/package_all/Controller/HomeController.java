package com.imtiaz.package_all.Controller;


import com.imtiaz.package_all.EntityModel.UserEntity;
import com.imtiaz.package_all.Helper.Message;
import com.imtiaz.package_all.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    // Home page controller
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    // About page controller
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }


    // Signup page controller
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Signup - Smart Contact Manager");
        model.addAttribute("user", new UserEntity());
        return "/Signup/signup";
    }


    // process the registration form
    @PostMapping("/do-register")
    public String doRegister(@Valid @ModelAttribute("user") UserEntity userEntity, Errors bindingResult,
                             @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
                             Model model,
                             HttpSession session){

        try {

            if (!agreement){
                System.out.println("You have not agreed the terms and conditions");
                throw new Exception("You have not agreed the terms and conditions");
            }

            if (bindingResult.hasErrors()){
                System.out.println("Error " + bindingResult.toString());
                model.addAttribute("user", userEntity);
                return "/Signup/signup";
            }

            userEntity.setRole("ROLE_USER");
            userEntity.setEnable(true);
            userEntity.setImageUrl("default.png");
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

            System.out.println(userEntity);

             userService.saveUser(userEntity);

            model.addAttribute("user", new UserEntity());
            session.setAttribute("message", new Message("Successfully Registered !!!", "alert-success"));
            return "/Signup/signup";



        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", userEntity);
            session.setAttribute("message", new Message("Something Went Wrong !!! Must agree terms and Condition ", "alert-danger"));
            return "/Signup/signup";
        }

    }


    // Handler for custom login
    @GetMapping("/sign-in")
    public String customLogin(Model model){
        model.addAttribute("title", "Login Page");
        return "login";
    }


}

// Last video 13
