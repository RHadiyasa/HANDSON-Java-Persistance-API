package com.hadiyasa.example_jpa;

import com.hadiyasa.example_jpa.entity.User;
import com.hadiyasa.example_jpa.repository.UserRepository;
import com.hadiyasa.example_jpa.repository.impl.UserRepositoryImpl;
import com.hadiyasa.example_jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;

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
            System.out.println("6. Exit");
            System.out.print("Choose menu: ");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    System.out.println("Add User");
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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
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

        User user = new User();
        user.setId(id);
        userRepository.deleteUser(user);
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
            System.out.println("-".repeat(65));
            System.out.printf("%-36s | %-10s | %-10s\n", "ID", "Name", "Balance");
            System.out.println("-".repeat(65));
            for (User user : users) {
                System.out.printf("%-36s | %-10s | %-10s\n", user.getId(), user.getName(), user.getBalance());
            }
            System.out.println("-".repeat(65));
        }
    }


}
