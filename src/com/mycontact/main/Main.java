package com.mycontact.main;

import java.util.*;


import com.mycontact.user.model.User;
import com.mycontact.auth.Authentication;
import com.mycontact.auth.strategy.BasicAuth;
import com.mycontact.view.View;

/**
 * Use Case 5: View Contact Details
 * This Use Case provides an option to view details of any specific Contact details
 * 
 * Main entry point of the My Contacts Application.
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
 * @version 5.0
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