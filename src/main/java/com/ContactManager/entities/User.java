package com.ContactManager.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import  javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Cant be Empty")
	private String first_name;
	private String last_name;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private boolean status;
	private String image_Url;
	@Column(length = 500)
	private String about;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Contact> contacts= new ArrayList<Contact>();
	
	
	
	
	 


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	public User(int id, String first_name, String last_name, String email, String password, String role, boolean status,
			String image_Url, String about, List<Contact> contacts) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
		this.image_Url = image_Url;
		this.about = about;
		this.contacts = contacts;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getImage_Url() {
		return image_Url;
	}


	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}





	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", status=" + status + ", image_Url=" + image_Url
				+ ", about=" + about + ", contacts=" + contacts + "]";
	}


 
	
	
	
	
	
	
	

}
