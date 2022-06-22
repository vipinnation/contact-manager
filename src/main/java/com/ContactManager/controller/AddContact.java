package com.ContactManager.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ContactManager.DAO.ContactRepo;
import com.ContactManager.DAO.UserRepo;
import com.ContactManager.entities.Contact;
import com.ContactManager.entities.User;
import com.ContactManager.helper.Message;

@Controller
@RequestMapping("/user")
public class AddContact {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ContactRepo contactRepo;

	@GetMapping("/addContact")
	public String addContact(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("title", "Add Contact - Smart Contact Manager ");
		System.out.println("ADD Contact Get Method");
		return "Profile/addContact";
	}

	@PostMapping("/addContact")
	public String addContactPost(Model model, @ModelAttribute("contact") Contact contact, Principal principal,
			HttpSession session

	) {
		try {

			if (contact.getName().length() == 0 || contact.getContactNumber().length() == 0) {
				if (contact.getName().length() == 0 && contact.getContactNumber().length() == 0) {
					System.out.println("Name and Number is Empty");
					model.addAttribute("message", "");
					throw new Exception("Name and Number can`t be Empty");
				}

				else if (contact.getName().length() == 0) {
					System.out.println("Name is empyt");
					model.addAttribute("message", "Name is Empty");
					throw new Exception("Name Can`t be Empty");
				}

				else if (contact.getContactNumber().length() == 0) {
					model.addAttribute("message", "Number is Empty");
					System.out.println("Number is Empty");
					throw new Exception("Number Can`t be Empty");
				}

			}

			else {

				String name = principal.getName();

				User user = userRepo.getUserbyUsername(name);
				contact.setUser(user);

				this.contactRepo.save(contact);
				 
			 

				session.setAttribute("message", new Message("Contact Added Successfully", "alert-success"));

			}

		} catch (Exception e) {

			session.setAttribute("message", new Message("" + e.getMessage(), "alert-danger"));
		}
		return "redirect:/user/addContact";

	}

}
