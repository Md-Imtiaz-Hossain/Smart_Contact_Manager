package com.imtiaz.package_all.Controller;


import com.imtiaz.package_all.Helper.Message;
import com.imtiaz.package_all.Service.UserService;
import com.imtiaz.package_all.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@Controller
public class HomeController {
	
	private @Autowired BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userservice;
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("tittle", "This is dynamic controller");
		return "home";
		
	}
	@RequestMapping(value = "/about")
	public String about() {
		return "about";
		
	}

	@RequestMapping(value = "/signup")
	public String singUp(Model model) {
		model.addAttribute("tittle", "Registration");
		model.addAttribute("users", new UserDTO());
		return "signup";
		
	}
	
	@RequestMapping(value = "/signup",method= RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("users")UserDTO users , BindingResult result1  , @RequestParam(value = "agreement",defaultValue = "false")
	boolean agreement , Model model , HttpSession session) {
		System.out.println("Agreement "+agreement);
		System.out.println("users "+users);
		try {
		if(!agreement) {
			System.out.println("you have not agreed the term and condition");
			model.addAttribute("users",users);
			throw new Exception("you have not agreed the term and condition");
			
		}
		if(result1.hasErrors()) {
			model.addAttribute("users", users);
		    System.out.println("Error"+result1.toString());
			return "signup";
		}
		users.setRole("ROLE_USER");
		users.setEnabled(true);
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		UserDTO result = userservice.save(users);
		session.setAttribute("message", new Message("Successfully Register ", "alert-success"));
		model.addAttribute("users", result);
		}catch(Exception e){
			model.addAttribute("users" , users);
			session.setAttribute("message", new Message("Something went wrong "+e.getMessage(), "alert-danger"));
			
			
		}
		return "signup";
		
	}
	
	@RequestMapping(value = "/signin")
	public String customLogin(Model model) {
	
		return "login";
		
	}
}
