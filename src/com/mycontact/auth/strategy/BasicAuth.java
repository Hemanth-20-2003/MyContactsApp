package com.mycontact.auth.strategy;
import java.util.Map;

import com.mycontact.auth.Authentication;
import com.mycontact.user.model.User;
import com.mycontact.user.service.UserService;
//This class provides basic email and password authentication.
public class BasicAuth implements Authentication{
	
	//A map representing the user database.
    private Map<String, User> userDatabase;
    
    //Constructs a BasicAuth object with the given user database.
    public BasicAuth(Map<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    
    //Authenticates a user using email and password.
    @Override
    public User authenticate(String email, String password) {
        User user = userDatabase.get(email);
        if (user != null) {
            String hashedInput = UserService.hashPassword(password);
            if (user.getPasswordHash().equals(hashedInput)) {
                return user;
            }
        }
        return null;
    }
}