package com.mycontact.user.model;

import java.util.ArrayList;
import java.util.List;

import com.mycontact.contact.model.Contact;
/**
 * Abstract class representing a system user.
 * 
 * A User contains authentication information (email and password hash),
 * personal information (name), and a list of associated contacts.
 * 
 * This class serves as a base class for different types of users
 * (e.g., AdminUser, RegularUser).
 */
public abstract class User {
    private String email;
    private String passwordHash;
    private String name;
    private List<Contact> contacts = new ArrayList<>();
    
    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully!");
    }
    
    public void viewContacts() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n\t Your Contacts");

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);

            System.out.println((i + 1) + ". Name   : " + c.getName());
            System.out.println("   Number : " + c.getNumber());
            System.out.println("   Email  : " + c.getEmail());
            System.out.println();
        }
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
