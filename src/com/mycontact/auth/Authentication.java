package com.mycontact.auth;

import com.mycontact.user.model.User;

//Authentication interface defines the contract for user authentication strategies.
public interface Authentication {
    User authenticate(String email, String password);

}
