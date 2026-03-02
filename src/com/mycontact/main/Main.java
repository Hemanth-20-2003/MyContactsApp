package com.mycontact.main;

import java.util.*;


import com.mycontact.user.model.User;
import com.mycontact.auth.Authentication;
import com.mycontact.auth.strategy.BasicAuth;
import com.mycontact.view.View;

/**
 * UC-11: Create Tag
 * The Create Tag feature allows a user to create custom tags (such as Family, Work, Friends) for organizing contacts. 
 * A dedicated `Tag` class is introduced with attributes like tag name and a list of associated contacts. Tags are stored under the user and can be reused while assigning them to contacts.
 * 
 * This class:
 * - Initializes the application
 * - Maintains the in-memory user database
 * - Tracks the currently logged-in user
 * - Controls the main application loop
 *
 * The application runs continuously until manually terminated.
 *
 * @author Developer
 * @version 11.0
 */
public class Main {

    public static Map<String, User> userDatabase = new HashMap<>();
    public static User currentUser = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Authentication auth = new BasicAuth(userDatabase);
        
        System.out.println("\n\n\t\tMY CONTACTS APP\n\n");
        
        while (true) {

            if (currentUser == null) {
                View.loggedOutView(sc, auth);
            } else {
                View.loggedInView(sc);
            }
        }
    }
}