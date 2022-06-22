package com.ContactManager.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ContactManager.DAO.UserRepo;
import com.ContactManager.entities.User;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 User userData = userRepo.getUserbyUsername(username);

		if (userData == null) {
			throw new UsernameNotFoundException("User Doesn`t Exists");
		}

		CustomUserDetail customUserDetail = new CustomUserDetail(userData);
		return customUserDetail;
		
		
	}
		//Java Brains
		/* Optional<User> user = userRepo.findByUserName(username);

	        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

	        return user.map(CustomUserDetail::new).get();
	}*/
	

}
