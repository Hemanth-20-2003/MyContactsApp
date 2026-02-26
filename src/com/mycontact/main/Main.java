package com.mycontact.main;

import java.util.Scanner;
import com.mycontact.user.model.User;
import com.mycontact.user.service.UserService;

import java.util.*;
import com.mycontact.auth.Authentication;
import com.mycontact.auth.strategy.BasicAuth;

public class Main {

    public static void main(String args[]) throws Exception {

        Map<String, User> userDatabase = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        User currentUser = null;   // instead of SessionManager

        System.out.print("\n\n\t\tUser System\n\n");

        while (true) {

            System.out.println("\n1. Register\n2. Login\n3. Profile\n4. Logout");
            int n = sc.nextInt();
            sc.nextLine();

            if (n == 1) {
                try {
                    System.out.println("Enter your name");
                    String name = sc.nextLine();

                    System.out.println("Enter your email");
                    String email = sc.nextLine();

                    System.out.println("Enter your password");
                    String password = sc.nextLine();

                    System.out.print("Free or Premium: ");
                    String type = sc.nextLine();

                    User user = userService.registerUser(type, name, email, password);

                    userDatabase.put(user.getEmail(), user);

                    System.out.println("User registered successfully!");

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (n == 2) {

                Authentication auth = new BasicAuth(userDatabase);

                System.out.println("Enter your email");
                String email = sc.nextLine();

                System.out.println("Enter your password");
                String password = sc.nextLine();

                User loginResult = auth.authenticate(email, password);

                if (loginResult != null) {
                    currentUser = loginResult;
                    System.out.println("Login Successful! Welcome " + currentUser.getName());
                } else {
                    System.out.println("Invalid Email or Password");
                }
            }

            else if (n == 3) {

                if (currentUser == null) {
                    System.out.println("Not logged in.");
                } else {
                    System.out.println("Name : " + currentUser.getName());
                    System.out.println("Email : " + currentUser.getEmail());
                }
            }

            else if (n == 4) {

                if (currentUser != null) {
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                } else {
                    System.out.println("No user is logged in.");
                }
            }
        }
    }
}