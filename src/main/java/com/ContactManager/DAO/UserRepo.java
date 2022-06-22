package com.ContactManager.DAO;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ContactManager.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.email = :email")
	public User getUserbyUsername(@Param("email") String email);

	 
	@Modifying
	@Query("UPDATE User u SET u.first_name  =:first_name , u.last_name =:last_name , u.about =:about , u.image_Url =:image_Url where id=:email")
	@Transactional
	public void updateUserByEmail(@Param("email") int email, @Param("first_name") String first_name,
			@Param("last_name") String last_name, @Param("about") String about, @Param("image_Url") String image_Url);

	
	@Query( value="select email from User  where email=:email" , nativeQuery=true)
	public String getEmailbyUserEmail(@Param("email") String email);
	
	
}
 
