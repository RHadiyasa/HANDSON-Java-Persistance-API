package com.hadiyasa.example_jpa;

import com.hadiyasa.example_jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /**
         * Object Relational Mapping
         * 1. ORM -> teknik pemetaan (mapping) database dengan object
         * 2. Bisa memanipulasi data dengan menggunakan objectnya (ORM akan translate ke SQL Query)
         * 3. ORM bertanggunjawab melakukan sinkronisasi data yang kita ubah melalui object ke database
         * 4. Kita bisa melakukan manipulasi data pada database hanya dengan melakukan perubahan pada object saja.
         *
         * JPA
         * 1. Standarisasi ORM untuk Java
         * 2. Bagian dari Java Enterprise (JAKARTA ENTERPRISE)
         * 3. Tidak perlu query SQL manual karena sudah dihandle sama JPA
         *
         * JPA Provider
         * - Hibernate
         */

        System.out.println("Hello World");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example_jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // input data -> Buat transaction
        entityManager.getTransaction().begin(); // START TRANSACTION

        // Create object User
        User eja = new User("C004", "Eja", 150000);
        // Save object to database
        entityManager.persist(eja);

        //Commit transaction
        entityManager.getTransaction().commit();

        // Query to database
        // Criteria Builder -> Object untuk membangun SQL Command (SELECT * FROM...)
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // Criteria Query -> Object untuk membangun DQL Command
        CriteriaQuery<User> query = cb.createQuery(User.class);

        // CriteriaUpdate -> untuk DML Update
        // CriteriaDelete -> untuk DML Delete

        // ROOT : Seperti FROM
        // Root<User> : Object yang merepresentasikan data pada table <User>
        Root<User> root = query.from(User.class);

        // query.select() -> membangun command SELECT...
        // root doang berarti SELECT *
        // root.get("propertyName") berarti kaya SELECT columnName
        query.select(root); // Berarti kita ambil semua column yang ada di table SELECT *

        List<User> resultList = entityManager.createQuery(query).getResultList();
        System.out.println(resultList);

        entityManager.close(); // Harus di close
        entityManagerFactory.close();
    }
}
