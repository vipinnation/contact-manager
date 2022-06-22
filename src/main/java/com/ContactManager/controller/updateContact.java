package com.ContactManager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ContactManager.DAO.ContactRepo;
import com.ContactManager.DAO.UserRepo;
import com.ContactManager.entities.Contact;
import com.ContactManager.entities.User;

@Controller
public class updateContact {

	@Autowired
	private ContactRepo contactRepo;

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/user/updateContact/{contactId}")
	public String updateUserContact(Model model, @ModelAttribute("contact") Contact contact, Principal principal) {

		String name = principal.getName();

		User user = userRepo.getUserbyUsername(name);
		contact.setUser(user);

		this.contactRepo.updateContactById(contact.getContactId(), contact.getName(), contact.getContactNumber(),
				contact.getEmail(), contact.getWork(), contact.getDescription());

		return "redirect:/user/dashboard";

	}
}
