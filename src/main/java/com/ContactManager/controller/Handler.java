package com.ContactManager.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Handler {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");

		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, HttpSession session, Principal principal) {

		Object attribute = session.getAttribute("session");

		try {
			if (attribute.equals(principal.getName())) {
				model.addAttribute("title", "Login - Smart Contact Manager");

				System.out.println("Login get Method");
				return "redirect:/user/dashboard";
			}

			else {
				model.addAttribute("title", "Login - Smart Contact Manager");

				System.out.println("Login get Method");
				return "login";
			}

		} catch (Exception e) {
			model.addAttribute("title", "Login - Smart Contact Manager");

			System.out.println("Login get Method");
			return "login";
		}
	}

}
