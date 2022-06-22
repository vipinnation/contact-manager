package com.ContactManager.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ContactManager.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

	// @Query("select u from Contact u WHERE u.user_id= :user_id")
	// public List<Contact> getContactByUserId(@Param("id") int user_id);

	@Query("select u from Contact u WHERE u.contactId= :id")
	public Contact findContactById(@Param("id") int id);

	@Modifying
	@Query("UPDATE Contact u SET  u.name =:name , u.contactNumber =:contactNumber , u.email =:email , u.work =:work, u.description =:description  where contactId =:id")
	@Transactional
	public void updateContactById(@Param("id") int id, @Param("name") String name,
			@Param("contactNumber") String contactNumber, @Param("email") String email,
			@Param("work") String work, 
			@Param("description") String description);

}
