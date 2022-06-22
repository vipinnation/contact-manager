package com.ContactManager.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ContactManager.DAO.UserRepo;
import com.ContactManager.entities.User;
import com.ContactManager.helper.Message;

@Controller
public class SignUpHandler {

	@Autowired
	public PasswordEncoder passwordEncoder;
	 
	@Autowired
	UserRepo userRepo;

	@GetMapping("/signup")
	public String signup(Model model, Principal principal, HttpSession session) {

		Object attribute = session.getAttribute("session");

		try {
			if (attribute.equals(principal.getName())) {

				return "redirect:/user/dashboard";
			}

			else {
				model.addAttribute("title", "SignUp - Smart Contact Manager");
				model.addAttribute("user", new User());
				System.out.println("Sign Up Get Method ");
				return "signup";

			}

		} catch (Exception e) {
			model.addAttribute("title", "SignUp - Smart Contact Manager");
			model.addAttribute("user", new User());
			System.out.println("Sign Up Get Method ");
			return "signup";
		}

	}

	@PostMapping("/signup")
	public String postSignUp(@Valid Model model, @ModelAttribute("user") User user,
			@RequestParam(value = "checkbox", defaultValue = "false") boolean checkbox,
			@RequestParam(value = "confirmPassword") String confirmPassword, HttpSession session,
			BindingResult bindingResult) {

		System.out.println("sign up post method");

		 
		try {

			if (checkbox) {
				
				String userEmail = this.userRepo.getEmailbyUserEmail(user.getEmail());
				
				System.out.println(userEmail);

				if(!userEmail.equalsIgnoreCase(user.getEmail()))
				{
					if (user.getPassword().equals(confirmPassword)) {
						model.addAttribute("user", new User());
						user.setRole("ROLE_USER");
						user.setPassword(passwordEncoder.encode(user.getPassword()));
						user.setStatus(true);
						User result = this.userRepo.save(user);
						System.out.println(result);
						model.addAttribute("message", new Message("Successfully Registered", "alert-success"));
						return "redirect:/signup";
					}

					else {
						System.out.println(user);
						// session.setAttribute("user", new User());
						throw new Exception("Password Mismatch");

					}
					
				}
				
				else
				{
					throw new Exception("User Registered with this Email");
				}
			}

			else {

				throw new Exception("Please Confirm CheckBox");
			}

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("user", user);
			model.addAttribute("title", "Sign Up - Smart Contact Manager");
			session.setAttribute("message", new Message("" + e.getMessage(), "alert-danger"));
			return "signup";

		}

	}
}
