package com.hadiyasa.example_jpa;

import com.hadiyasa.example_jpa.repository.UserRepository;
import com.hadiyasa.example_jpa.repository.impl.UserRepositoryImpl;
import com.hadiyasa.example_jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        UserRepository userRepository = new UserRepositoryImpl(entityManager);

        Menu menu = new Menu(userRepository);
        menu.displayMenu();

        entityManager.close();
        JpaUtil.shutDown();
    }
}
