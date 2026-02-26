package com.mycontact.auth;

import com.mycontact.user.model.User;


public interface Authentication {
    User authenticate(String email, String password);

}
