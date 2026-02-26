package com.mycontact.main;

import java.util.Scanner;
import com.mycontact.user.model.User;
import com.mycontact.user.service.UserService;

public class Main {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.print("\n\n\t\tUser Registration\n\n");

        System.out.println("Free or Premium");
        String type = sc.nextLine();

        System.out.println("Enter your name");
        String name = sc.nextLine();

        System.out.println("Enter your email");
        String email = sc.nextLine();

        System.out.println("Enter your password");
        String password = sc.nextLine();

        User user = userService.registerUser(type, name, email, password);

        System.out.println("\nUser registered successfully");
        System.out.println("Email : " + user.getEmail());
        System.out.println("Name : " + user.getName());
    }
}