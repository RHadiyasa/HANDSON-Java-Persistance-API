# JAVA PERSISTENCE API

## Object Relational Mapping
* ORM -> teknik pemetaan (mapping) database dengan object
* Bisa memanipulasi data dengan menggunakan objectnya (ORM akan translate ke SQL Query)
* ORM bertanggunjawab melakukan sinkronisasi data yang kita ubah melalui object ke database
* Kita bisa melakukan manipulasi data pada database hanya dengan melakukan perubahan pada object saja.
## JPA
* Standarisasi ORM untuk Java
* Bagian dari Java Enterprise (JAKARTA ENTERPRISE)
* Tidak perlu query SQL manual karena sudah dihandle sama JPA

## JPA Provider
* Hibernate
```java


        import com.hadiyasa.example_jpa.entity.User;
        import jakarta.persistence.EntityManager;
        import jakarta.persistence.EntityManagerFactory;
        import jakarta.persistence.Persistence;
        import jakarta.persistence.criteria.CriteriaBuilder;
        import jakarta.persistence.criteria.CriteriaQuery;
        import jakarta.persistence.criteria.Root;
        
        import java.util.List;        

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example_jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /**
         * Method CRUD dari entity manager
         * 1. persist() : Menyimpan data ke database
         * 2. merge()   : Update data ke database
         * 3. remove()  : Remove data
         * */
        // Create object User
        User user = new User(null, "Rani", 634000);

        // input data -> Buat transaction
        entityManager.getTransaction().begin(); // START TRANSACTION

        /** Save object to database*/
         entityManager.persist(user);

        /** Update object to database*/
        // entityManager.merge(user);

        /** Delete Data */
          /*User currentUser = entityManager.find(User.class, 5); // ID dari user di atas
          entityManager.remove(currentUser);*/

        //Commit transaction
        entityManager.getTransaction().commit();

        /**------------------------------------------------------*/

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
```