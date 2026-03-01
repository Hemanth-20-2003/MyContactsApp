package com.mycontact.contact.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycontact.tag.model.Tag;

//Abstract base class representing a Contact.
public abstract class Contact {
	private String name;
	private String number;
	private String email;
	private List<Tag> tags = new ArrayList<>();
    private LocalDateTime dateAdded;
    private boolean frequentlyContacted; // dummy
    
    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
            if (!tag.getContacts().contains(this)) {
                tag.getContacts().add(this);
            }
        }
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
    
    public Contact() {
        this.dateAdded = LocalDateTime.now();
        this.frequentlyContacted = false; // default
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDateAdded() {
	    return dateAdded;
	}

	public boolean isFrequentlyContacted() {
	    return frequentlyContacted;
	}

	public void setFrequentlyContacted(boolean value) {
	    this.frequentlyContacted = value;
	}
}
