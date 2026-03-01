package com.mycontact.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.mycontact.main.Main;
import com.mycontact.user.model.User;
import com.mycontact.user.service.UserService;
import com.mycontact.auth.Authentication;
import com.mycontact.contact.model.Contact;
import com.mycontact.contact.model.Organization;
import com.mycontact.contact.model.Person;

/**
 * View class handles all console-based user interactions.
 * 
 * It manages both:
 * 1. Logged-out user operations (Register/Login)
 * 2. Logged-in user operations (Profile, Contacts, Logout)
 * 
 * This class acts as the presentation layer of the application.
 */

public class View {
	//View for the loggedOut users.
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

	//View for LoggedInUsers
	public static void loggedInView(Scanner sc) {

		System.out.println("\n1. Profile");
		System.out.println("2. Contacts");
		System.out.println("3. Logout");

		int choice = sc.nextInt();
		sc.nextLine();

		// PROFILE 
		if (choice == 1) {

			System.out.println("\n--- Profile Info ---");
			System.out.println("Name  : " + Main.currentUser.getName());
			System.out.println("Email : " + Main.currentUser.getEmail());

			System.out.println("\n1. Edit");
			System.out.println("2. Close");

			int pChoice = sc.nextInt();
			sc.nextLine();

			if (pChoice == 1) {

				System.out.println("\n1. Edit Name");
				System.out.println("2. Edit Email");
				System.out.println("3. Edit Password");

				int editChoice = sc.nextInt();
				sc.nextLine();

				if (editChoice == 1) {

					System.out.println("Enter new name:");
					String newName = sc.nextLine();
					Main.currentUser.setName(newName);
					System.out.println("Name updated successfully!");

				} else if (editChoice == 2) {

					System.out.println("Enter new email:");
					String newEmail = sc.nextLine();

					if (!UserService.isValidEmail(newEmail)) {
						System.out.println("Invalid Email");
						return;
					}

					Main.userDatabase.remove(Main.currentUser.getEmail());
					Main.currentUser.setEmail(newEmail);
					Main.userDatabase.put(newEmail, Main.currentUser);

					System.out.println("Email updated successfully!");

				} else if (editChoice == 3) {

					try {
						System.out.println("Enter new password:");
						String newPassword = sc.nextLine();
						String hashed = UserService.hashPassword(newPassword);
						Main.currentUser.setPasswordHash(hashed);
						System.out.println("Password updated successfully!");
					} catch (Exception e) {
						System.out.println("Error updating password");
					}
				}
			}
		}


		// CONTACTS 
		else if (choice == 2) {

			while (true) {

				System.out.println("\n--- Your Contacts ---");

				List<Contact> contacts = Main.currentUser.getContacts();

				if (contacts.isEmpty()) {
					System.out.println("No contacts found.");
				} else {
					for (int i = 0; i < contacts.size(); i++) {
						System.out.println((i + 1) + ". " + contacts.get(i).getName());
					}
				}

				System.out.println((contacts.size() + 1) + ". Add Contact");
				System.out.println((contacts.size() + 2) + ". Bulk Operations");
				System.out.println((contacts.size() + 3) + ". Close");

				int cChoice = sc.nextInt();
				sc.nextLine();

				// View Contact Details
				if (cChoice >= 1 && cChoice <= contacts.size()) {

					Contact selected = contacts.get(cChoice - 1);


					while (true) {

						System.out.println("\n--- Contact Details ---");
						System.out.println("Name   : " + selected.getName());
						System.out.println("Number : " + selected.getNumber());
						System.out.println("Email  : " + selected.getEmail());

						System.out.println("\n1. Edit");
						System.out.println("2. Delete");
						System.out.println("3. Close");

						int detailChoice = sc.nextInt();
						sc.nextLine();

						// EDIT
						if (detailChoice == 1) {

							while (true) {
								System.out.println("\n--- Edit Contact ---");
								System.out.println("1. Edit Name");
								System.out.println("2. Edit Number");
								System.out.println("3. Edit Email");
								System.out.println("4. Close");

								int editChoice = sc.nextInt();
								sc.nextLine();

								if (editChoice == 1) {
									System.out.println("Enter new name:");
									selected.setName(sc.nextLine());
									System.out.println("Name updated successfully!");
								} 
								else if (editChoice == 2) {
									System.out.println("Enter new number:");
									selected.setNumber(sc.nextLine());
									System.out.println("Number updated successfully!");
								} 
								else if (editChoice == 3) {
									System.out.println("Enter new email:");
									selected.setEmail(sc.nextLine());
									System.out.println("Email updated successfully!");
								} 
								else if (editChoice == 4) {
									break;
								} 
								else {
									System.out.println("Invalid choice.");
								}
							}
						}

						// DELETE
						else if (detailChoice == 2) {

							System.out.println("Are you sure you want to delete this contact? (y/n)");
							String confirm = sc.nextLine();

							if (confirm.equalsIgnoreCase("y")) {
								Main.currentUser.deleteContact(cChoice - 1);
								System.out.println("Contact deleted successfully!");
								break;  // Exit after deletion
							}
						}

						// CLOSE
						else if (detailChoice == 3) {
							break;
						}

						else {
							System.out.println("Invalid choice.");
						}
					}
				}

				// Add Contact
				else if (cChoice == contacts.size() + 1) {

					System.out.println("Select Contact Type:");
					System.out.println("1. Person");
					System.out.println("2. Organisation");

					int typeChoice = sc.nextInt();
					sc.nextLine();

					System.out.println("Enter contact name:");
					String name = sc.nextLine();

					System.out.println("Enter contact number:");
					String number = sc.nextLine();

					System.out.println("Enter contact email:");
					String email = sc.nextLine();

					Contact contact;

					if (typeChoice == 1) {
						contact = new Person();
					} else if (typeChoice == 2) {
						contact = new Organization();
					} else {
						System.out.println("Invalid contact type.");
						continue;
					}

					contact.setName(name);
					contact.setNumber(number);
					contact.setEmail(email);

					Main.currentUser.addContact(contact);

					System.out.println("Contact added successfully!");
				}

				//Bulk Operations
				else if (cChoice == contacts.size() + 2) {

				    List<Integer> selectedIndexes = new ArrayList<>();

				    System.out.println("Enter contact numbers to select (0 to stop):");

				    while (true) {
				        int index = sc.nextInt();
				        sc.nextLine();

				        if (index == 0) break;

				        if (index >= 1 && index <= contacts.size()) {
				            selectedIndexes.add(index - 1);
				            System.out.println("Added: " + contacts.get(index - 1).getName());
				        } else {
				            System.out.println("Invalid contact number.");
				        }
				    }

				    if (selectedIndexes.isEmpty()) {
				        System.out.println("No contacts selected.");
				        continue;
				    }

				    System.out.println("\nBulk Options:");
				    System.out.println("1. Delete");
				    System.out.println("2. Tag");
				    System.out.println("3. Export");

				    int bulkChoice = sc.nextInt();
				    sc.nextLine();

				    // DELETE
				    if (bulkChoice == 1) {

				        // Remove from highest index to lowest to avoid shifting issue
				        Collections.sort(selectedIndexes, Collections.reverseOrder());

				        for (int i : selectedIndexes) {
				            Main.currentUser.deleteContact(i);
				        }

				        System.out.println("Selected contacts deleted successfully!");
				    }

				    // TAG
				    else if (bulkChoice == 2) {

				        System.out.println("Enter tag name:");
				        String tag = sc.nextLine();

				        for (int i : selectedIndexes) {
				            contacts.get(i).addTag(tag);
				        }

				        System.out.println("Tag added to selected contacts!");
				    }

				    // EXPORT (Dummy)
				    else if (bulkChoice == 3) {
				        System.out.println("Exported");
				    }

				    else {
				        System.out.println("Invalid option.");
				    }
				}
				
				// Close
				else if (cChoice == contacts.size() + 3) {
					break;
				}

				else {
					System.out.println("Invalid choice.");
				}
			}
		}

		else if (choice == 3) {

			Main.currentUser = null;
			System.out.println("Logged out successfully.");
		}
	}
}