package com.mycontact.auth.strategy;
import java.util.Map;

import com.mycontact.auth.Authentication;
import com.mycontact.user.model.User;
import com.mycontact.user.service.UserService;

public class BasicAuth implements Authentication{
    private Map<String, User> userDatabase;
    public BasicAuth(Map<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }
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