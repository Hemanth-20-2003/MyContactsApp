package com.mycontact.user.service;

import com.mycontact.user.model.*;
import java.security.MessageDigest;

public class UserService {

    public User registerUser(String type, String name, String email, String password) throws Exception {

        // Validate email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid Email");
        }

        User user;

        if (type.equalsIgnoreCase("FREE")) {
            user = new FreeUser();
        } else if (type.equalsIgnoreCase("PREMIUM")) {
            user = new PremiumUser();
        } else {
            throw new IllegalArgumentException("Invalid User Type");
        }

        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(hashPassword(password));

        return user;
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}