package com.ContactManager.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ContactManager.DAO.ContactRepo;
import com.ContactManager.DAO.UserRepo; 
import com.ContactManager.entities.User;

@Controller
@RequestMapping("/user")
public class SettingController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ContactRepo contactRepo;

	@GetMapping("/setting")
	public String setting(Model model, Principal pal) {
		model.addAttribute("username", pal.getName());
		User user = this.userRepo.getUserbyUsername(pal.getName());

		model.addAttribute("user", user);

		model.addAttribute("title", "Setting - Smart Contact Manager");
		return "Profile/Setting.html";
	}

	@PostMapping("/updateUser/{userId}")
	public String updateUser(Model model, Principal principal, @ModelAttribute("user") User userInput) {

		User user2 = this.userRepo.getUserbyUsername(principal.getName());

		model.addAttribute("user", user2);

		this.userRepo.updateUserByEmail(userInput.getId(), userInput.getFirst_name(), userInput.getLast_name(),
				userInput.getAbout(), userInput.getImage_Url());

		return "Profile/Setting";
	}

	@GetMapping("deleteUserContact/{userId}")
	public String deleteContact(@PathVariable("userId") String userId) {

		User user = this.userRepo.getUserbyUsername(userId);

		this.contactRepo.deleteAllInBatch();

		return "redirect:/user/dashboard";
	}

	@GetMapping("deactivateUser/{userId}")
	public String deactivateUser(@PathVariable("userId") String userId, HttpSession session) {
		try {
			User user = this.userRepo.getUserbyUsername(userId);

			this.contactRepo.deleteAllInBatch();
			this.userRepo.deleteById(user.getId());
			session.removeAttribute("session");
			

		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}

		return "redirect:/logout";
	}

}
