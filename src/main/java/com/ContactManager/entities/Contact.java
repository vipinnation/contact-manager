package com.ContactManager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;
	private String name;
	private String contactNumber;
	@Column(length = 1000)
	private String description;
	private String work;
	private String email;
	private String imageContact;
	
	@ManyToOne
	private User user;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(int contactId, String name, String contactNumber, String description, String work, String email,
			String imageContact) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.contactNumber = contactNumber;
		this.description = description;
		this.work = work;
		this.email = email;
		this.imageContact = imageContact;
	}
	

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageContact() {
		return imageContact;
	}

	public void setImageContact(String imageContact) {
		this.imageContact = imageContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", name=" + name + ", contactNumber=" + contactNumber
				+ ", description=" + description + ", work=" + work + ", email=" + email + ", imageContact="
				+ imageContact + ", user=" + user + "]";
	}
	
	
	
	
	
	
}
