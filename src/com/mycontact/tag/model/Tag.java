package com.mycontact.tag.model;

import java.util.ArrayList;
import java.util.List;

import com.mycontact.contact.model.Contact;

public class Tag {

    private String name;
    private List<Contact> contacts;

    public Tag(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    // Add contact to tag (bidirectional)
    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            contact.addTag(this);   // sync both sides
        }
    }

    // Remove contact from tag
    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.removeTag(this);
    }
}