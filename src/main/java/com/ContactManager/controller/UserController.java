package com.ContactManager.controller;

import java.security.Principal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

import com.ContactManager.DAO.ContactRepo;
import com.ContactManager.DAO.UserRepo;
import com.ContactManager.entities.Contact;
import com.ContactManager.entities.User;
import com.ContactManager.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ContactRepo contactRepo;

	@GetMapping("/dashboard")
	public String index(Model model, Principal principal, HttpSession session) {

		model.addAttribute("username", principal.getName());
		model.addAttribute("title", "Dashboard - Smart Contact Manager");
		System.out.println("User Method");

		User user = this.userRepo.getUserbyUsername(principal.getName());
	 
			List<Contact> contacts = user.getContacts();
		
		 model.addAttribute("contacts",contacts);
		
		 

		session.setAttribute("session", principal.getName());

		return "Profile/dashboard";
	}

	@GetMapping("/profile")
	public String profile(Model model, Principal principal) {
		User user = this.userRepo.getUserbyUsername(principal.getName());
		String username = user.getFirst_name() + "  " + user.getLast_name();
		model.addAttribute("user", user);
		model.addAttribute("fullName", username);
		model.addAttribute("username", principal.getName());

		return "Profile/profile";

	}

}
