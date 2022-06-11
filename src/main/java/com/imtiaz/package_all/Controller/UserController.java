package com.imtiaz.package_all.Controller;


import com.imtiaz.package_all.Helper.Message;
import com.imtiaz.package_all.Service.ContactService;
import com.imtiaz.package_all.Service.UserService;
import com.imtiaz.package_all.dto.ContactDTO;
import com.imtiaz.package_all.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ContactService contactSevice;

	@RequestMapping(value = "/index")
	public String dashboard(Model model, Principal principle) {

		return "normal/user_dashboard";

	}

	@ModelAttribute
	public void commonData(Model model, Principal principle) {
		String username = principle.getName();
		System.out.println("Username " + username);
		UserDTO userDTO = userService.getUserDetailsByUserName(username);
		model.addAttribute("userDTO", userDTO);

	}

	@GetMapping(value = "/add_contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("contact", new ContactDTO());
		return "normal/add_contact";

	}

	@PostMapping(value = "/process_contact")
	public String addContact(@ModelAttribute ContactDTO contactdto, @RequestParam("profileImage") MultipartFile file,
			Principal principle, HttpSession session) {
		System.out.println("ConatctDTO " + contactdto);

		String userName = principle.getName();
		boolean value = userService.saveContact(userName, file, contactdto);
		if (value) {
			session.setAttribute("message", new Message("Contact Added sucessfully!! Add more.", "success"));
		} else {
			session.setAttribute("message", new Message("something went wrong", "danger"));
		}
		return "normal/add_contact";

	}

	@GetMapping(value = "/view_contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page, Model model, Principal principal) {
		Page<ContactDTO> contactDTOList = contactSevice.findContactListByUserId(principal.getName(), page);
		contactDTOList.forEach(e -> System.out.println(e.getImage()));
		model.addAttribute("contacts", contactDTOList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", contactDTOList.getTotalPages());
		return "normal/view_contact";

	}

	@GetMapping("/contact/{cid}")
	public String contact(@PathVariable("cid") Long cid, Model model, Principal principal) {
		ContactDTO contactDTO = contactSevice.findContactDetail(cid);
		Long userId = userService.getUserDetailsByUserName(principal.getName()).getId();

		if (contactDTO.getUserId() == userId) {
			System.out.println("cid " + cid);
			model.addAttribute("contact", contactDTO);
		}
		System.out.println("contactDto " + contactDTO);
		return "normal/contact_detail";

	}

	@PostMapping("/contact/delete/{cId}")
	public String deleteContact(@PathVariable("cId") Long cId, Model model, Principal principal, HttpSession session) {
		contactSevice.deleteContactById(cId, principal.getName());
		session.setAttribute("message", new Message("Contact deleted successfully", "success"));
		return "redirect:/user/view_contacts/0";
	}

	// open update form handler
	@PostMapping("/contact/update/{cid}")
	public String updateContact(@PathVariable("cid") Long cid, Model m) {
		m.addAttribute("tittle", "Update Contact");
		ContactDTO contact = contactSevice.findContactDetail(cid);
		m.addAttribute("contact", contact);
		return "normal/update_contact";

	}

	@PostMapping(value = "contact/process-update/{cid}")
	public String UpdateHandler(@ModelAttribute ContactDTO contact, @RequestParam("profileImage") MultipartFile file,
			 HttpSession session, Principal principal) {
		if (contactSevice.updateContact(contact, file, principal.getName())) {
			session.setAttribute("message", new Message("Contact Updated Successfully", "success"));

		} else {
			session.setAttribute("message", new Message("Contact not Updated ", "danger"));
		}
		return "redirect:/user/contact/{cid}";

	}
	
	@GetMapping(value = "/profile")
	public String yourProfile(Model model,Principal principal) {	
		model.addAttribute("tittle","Your Profile");
		model.addAttribute("user", userService.getUserDetailsByUserName(principal.getName()));
		return "normal/profile";
	
		
	}
	
	@GetMapping(value = "/update")
	public String updateProfile(Principal principal,Model model) {
		model.addAttribute("user", userService.getUserDetailsByUserName(principal.getName()));
		return "normal/update_profile";
		
	}
	
	@PostMapping(value= "/handle_update_profile_req")
	public String updateProfilerequest(@ModelAttribute UserDTO userDTO,@RequestParam("profile") MultipartFile file,HttpSession httpSession ) {
		if (userService.update(userDTO, file)) {
			httpSession.setAttribute("message", new Message("User Updated Successfully", "success"));

		} else {
			httpSession.setAttribute("message", new Message("User not Updated ", "danger"));
			
		}
		
		return "redirect:/user/profile";
		
	}
	
	@PostMapping("/delete")
	public String deleteUser(Principal principal, HttpSession session) {
		
		if (userService.deleteUser(principal.getName())) {
			session.setAttribute("message", new Message("User deleted Successfully", "success"));
			return "redirect:/logout";

		} else {
			session.setAttribute("message", new Message("User not deleted ", "danger"));
			
		}
		
		return "redirect:/logout";
	}

}
