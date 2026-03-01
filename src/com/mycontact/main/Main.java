package com.mycontact.main;

import java.util.*;


import com.mycontact.user.model.User;
import com.mycontact.auth.Authentication;
import com.mycontact.auth.strategy.BasicAuth;
import com.mycontact.view.View;

/**
 * ## UC-10: Apply Filters on Contacts
 * 
 * The Apply Filters feature allows a user to filter contacts based on specific criteria such as tag, date added, or frequently contacted status. 
 * The `dateAdded` field is automatically assigned when a contact is created, while the frequently contacted option is currently a dummy flag. This feature helps users quickly narrow down and manage contacts efficiently using structured filtering options.

 * This class:
 * - Initializes the application
 * - Maintains the in-memory user database
 * - Tracks the currently logged-in user
 * - Controls the main application loop
 *
 * The application runs continuously until manually terminated.
 *
 * @author Developer
 * @version 10.0
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