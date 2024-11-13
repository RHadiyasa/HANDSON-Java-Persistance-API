package com.hadiyasa.example_jpa.repository;

import com.hadiyasa.example_jpa.entity.User;

import java.util.List;

public interface UserRepository {
    /**
     * User Repository for method interface
     * */
    void addUser(User user);
    User findByName(String name);
    List<User> findAll();
    void updateUser(User user);
    void deleteUser(String id);
    User findByUsernameAndPassword(String username, String password);
    User userDetails(String id);
}
