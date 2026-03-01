package com.mycontact.main;

import java.util.*;


import com.mycontact.user.model.User;
import com.mycontact.auth.Authentication;
import com.mycontact.auth.strategy.BasicAuth;
import com.mycontact.view.View;

/**
 * UC-09: Search Contacts
 * The Search Contacts feature allows a user to search contacts by name, phone number, email, or tags using regular expressions.  
 * After selecting a search category, the user enters a keyword (e.g., "ra"), and the system displays all matching contacts with their original index numbers.  
 * The user can then select a specific contact by entering its displayed number to view detailed information.
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
 * @version 9.0
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