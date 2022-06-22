package com.ContactManager.controller;
 
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.ContactManager.DAO.ContactRepo;
import com.ContactManager.DAO.UserRepo;
import com.ContactManager.entities.Contact;
import com.ContactManager.entities.User;
import com.ContactManager.helper.Message;

@Controller
@RequestMapping("/user/")
public class MyContact {

	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private UserRepo userRepo;

	@GetMapping("/deleteContact/{id}")
	public String deleteContact(@PathVariable(name = "id") int id, HttpSession session) {

		System.out.println("contact deleted" + id);
		this.contactRepo.deleteById(id);
		session.setAttribute("message", new Message("Contact Deleted Successfully", "alert-success"));

		 
		return "redirect:/login";
		
	}

	@GetMapping("/viewContact/{contactId}")
	public String viewContact(@PathVariable(name = "contactId") int id,Model model,Principal principal) {
		
		 Contact findContactById = contactRepo.findContactById(id);
		 	
		  
		model.addAttribute("contact",findContactById);
		 
		model.addAttribute("username",principal.getName());
		
		return "Profile/viewContact";

	}
	

}
