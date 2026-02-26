package com.mycontact.view;

import java.util.Scanner;

import com.mycontact.main.Main;
import com.mycontact.user.model.User;
import com.mycontact.user.service.UserService;
import com.mycontact.auth.Authentication;

public class View {

    public static void loggedOutView(Scanner sc, Authentication auth) {
        System.out.println("\n1. Register New User");
        System.out.println("2. Login");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            try {
                System.out.println("Enter name:");
                String name = sc.nextLine();

                System.out.println("Enter email:");
                String email = sc.nextLine();

                System.out.println("Enter password:");
                String password = sc.nextLine();

                System.out.println("Free or Premium:");
                String type = sc.nextLine();

                User user = UserService.registerUser(type, name, email, password);
                Main.userDatabase.put(user.getEmail(), user);

                System.out.println("User Registered Successfully!");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 2) {

            System.out.println("Enter email:");
            String email = sc.nextLine();

            System.out.println("Enter password:");
            String password = sc.nextLine();

            User user = auth.authenticate(email, password);

            if (user != null) {
                Main.currentUser = user;
                System.out.println("Login Successful! Welcome " + user.getName());
            } else {
                System.out.println("Invalid Credentials.");
            }
        }
    }
    public static void loggedInView(Scanner sc) {

        System.out.println("\n1. Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. Logout");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1){
            System.out.println("Name: " + Main.currentUser.getName());
            System.out.println("Email: " + Main.currentUser.getEmail());

        } else if (choice == 2) {
            System.out.println("\n1. Edit Name");
            System.out.println("2. Edit Password");
            System.out.println("3. Edit Email");

            int editChoice=sc.nextInt();
            sc.nextLine();

            if (editChoice == 1){

                System.out.println("Enter new name:");
                String newName = sc.nextLine();
                Main.currentUser.setName(newName);
                System.out.println("Name Updated Successfully!");

            } else if (editChoice == 2){
                try {
                    System.out.println("Enter new password:");
                    String newPassword = sc.nextLine();
                    String hashed = UserService.hashPassword(newPassword);
                    Main.currentUser.setPasswordHash(hashed);
                    System.out.println("Password Updated Successfully!");
                } catch (Exception e) {
                    System.out.println("Error updating password");
                }

            } else if (editChoice == 3){

                try {
                    System.out.println("Enter new email:");
                    String newEmail = sc.nextLine();

                    if (!UserService.isValidEmail(newEmail)){
                        throw new IllegalArgumentException("Invalid Email");
                    }

                    // remove old email entry
                    Main.userDatabase.remove(Main.currentUser.getEmail());

                    Main.currentUser.setEmail(newEmail);

                    // add updated entry
                    Main.userDatabase.put(newEmail,Main.currentUser);

                    System.out.println("Email Updated Successfully!");

                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        } else if (choice == 3) {

            Main.currentUser = null;
            System.out.println("Logged Out Successfully.");
        }
    }
}