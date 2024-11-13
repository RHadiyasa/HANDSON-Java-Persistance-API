package com.hadiyasa.example_jpa.repository.impl;

import com.hadiyasa.example_jpa.entity.User;
import com.hadiyasa.example_jpa.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    @Override
    public User findByName(String name) {
        //return entityManager.find(User.class, name); // Menggunakan PK

        /** JPQL (Java Persistence Query Language)
         * Bahasa query yang dirancang khusus untuk bekerja dengan entitas dalam JPA
         * (Bukan langsung ke dalam Table seperti SQL)
         * */
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        return user;
    }

    @Override
    public User userDetails(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.name = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password",password)
                    .getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);

        return entityManager
                .createQuery(query)
                .getResultList();
    }

    @Override
    public void updateUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
    }

    @Override
    public void deleteUser(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User currentUser = entityManager.find(User.class, id);
        // check apakah ID nya ada?
        if (currentUser != null) {
            entityManager.remove(currentUser);
        }
        transaction.commit();
    }
}
