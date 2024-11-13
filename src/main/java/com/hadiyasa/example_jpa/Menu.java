package com.hadiyasa.example_jpa;

import com.hadiyasa.example_jpa.entity.User;
import com.hadiyasa.example_jpa.entity.UserProfile;
import com.hadiyasa.example_jpa.repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final UserRepository userRepository;

    public Menu(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void displayMenu() {
        System.out.println("-".repeat(40));
        System.out.println("Java Persistence API");
        System.out.println("-".repeat(40));

        while (true) {
            System.out.println("1. Add User");
            System.out.println("2. Find User");
            System.out.println("3. Update User");
            System.out.println("4. View Users");
            System.out.println("5. Delete User");
            System.out.println("6. View details");
            System.out.println("0. Exit");
            System.out.print("Choose menu: ");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    System.out.println("Add new User");
                    addUser();
                    break;
                case 2:
                    System.out.println("Find User");
                    searchUser();
                    break;
                case 3:
                    System.out.println("Update User");
                    updateUser();
                    break;
                case 4:
                    System.out.println("All Users");
                    viewUsers();
                    break;
                case 5:
                    System.out.println("Delete User");
                    deleteUser();
                    break;
                case 6:
                    System.out.println("View details");
                    viewUserDetails();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private void viewUserDetails(){
        System.out.print("Enter UserProfile ID: ");
        String id = scanner.nextLine();

        User user = userRepository.userDetails(id);

        if (user != null) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Balance: " + user.getBalance());

            if (user.getUserProfile() != null) {
                System.out.println("Profile ID: " + user.getUserProfile().getId());
                System.out.println("Account Number: " + user.getUserProfile().getAccountNumber());
                System.out.println("Address: " + user.getUserProfile().getAddress());
                System.out.println("Phone Number: " + user.getUserProfile().getPhoneNumber());
            } else {
                System.out.println("User profile is not set...");
                System.out.print("Add detail profile?(Y/N): ");
                String addDetail = scanner.nextLine();
                if (addDetail.equalsIgnoreCase("Y")) {
                    System.out.print("Add Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Add Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Add Address: ");
                    String address = scanner.nextLine();

                    UserProfile userProfile = new UserProfile();
                    userProfile.setAccountNumber(accountNumber);
                    userProfile.setAddress(address);
                    userProfile.setPhoneNumber(phoneNumber);

                    user.setUserProfile(userProfile); // set ke dalam column User
                    userRepository.updateUser(user); // update user setelah memiliki userProfile

                    System.out.println("User profile updated");
                }
            }
        } else {
            System.out.println("User does not exist...");
        }

        System.out.println("-".repeat(40));
    }

    private void updateUser(){
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Update Balance: ");
        int updateBalance = Integer.parseInt(scanner.nextLine());

        User user = new User(id, username, updateBalance);
        userRepository.updateUser(user);
        System.out.println("User updated");
    }

    private void deleteUser(){
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        userRepository.deleteUser(id);
        System.out.println("User deleted...");
    }

    private void addUser(){
        System.out.print("Enter Username: ");
        String name = scanner.nextLine();
        System.out.print("Enter Balance: ");
        int balance = Integer.parseInt(scanner.nextLine());

        User user = new User(null, name, balance);
        userRepository.addUser(user);
        System.out.println("User successfully added...!");
    }

    private void searchUser(){
        System.out.print("Enter Username: ");
        String name = scanner.nextLine();

        User user = userRepository.findByName(name);

        if (user != null) {
            System.out.println("User found!");
            System.out.println("-".repeat(40));
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Balance: " + user.getBalance());
            System.out.println("-".repeat(40));
        } else {
            System.out.println("User not found.");
        }
    }

    private void viewUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("There are no users");
        } else {
            System.out.println("There are " + users.size() + " users");
            System.out.println("-".repeat(80));
            System.out.printf("%-36s | %-10s | %-10s | %-10s\n", "ID", "Name", "Balance", "User Profile");
            System.out.println("-".repeat(80));
            for (User user : users) {
                String userProfileId = (user.getUserProfile() != null && user.getUserProfile().getId() != null)
                        ? user.getUserProfile().getId()
                        : "No Profile";
                System.out.printf("%-36s | %-10s | %-10s | %-10s\n",
                        user.getId(), user.getName(),
                        user.getBalance(), userProfileId);
            }
            System.out.println("-".repeat(80));
        }
    }
}
