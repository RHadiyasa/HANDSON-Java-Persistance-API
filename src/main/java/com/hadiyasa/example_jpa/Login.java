package com.hadiyasa.example_jpa;

import com.hadiyasa.example_jpa.entity.User;
import com.hadiyasa.example_jpa.repository.UserRepository;
import com.hadiyasa.example_jpa.repository.impl.UserRepositoryImpl;
import com.hadiyasa.example_jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;


public class Login {
    public void loginUser(){
        showMenu();
    }

    public void showMenu(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        UserRepository userRepository = new UserRepositoryImpl(entityManager);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = userRepository.findByUsernameAndPassword(username, password);

        if(user == null){
            System.out.println("Invalid username or password");
            return;
        }

        System.out.println("You are logged in!");
        Menu menu = new Menu(userRepository);
        menu.displayMenu();

        entityManager.close();
        JpaUtil.shutDown();
    }
}
