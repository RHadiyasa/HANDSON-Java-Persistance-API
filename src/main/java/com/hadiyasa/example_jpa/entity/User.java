package com.hadiyasa.example_jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * @Entity  : Menandakan bahwa kelas ini mewakili sebuah entity dalam JPA yang akan dipetakan ke dalam tabel pada database
 * @Table   : Mengatur nama tabel di database, dalam hal ini menggunakan nama "users" untuk tabel yang berhubungan dengan kelas User
 * @Id      : Menandakan kolom ini sebagai primary key dalam tabel
 * @Column  : Mengatur nama kolom di tabel sebagai "user_name" dan mengatur constraint pada kolom
 * @GeneratedValue  : Untuk generate value otomatis
 *      Strategy    -> Jenis generate id dari provider database, SEQUENCE (postgresql)
 *      Generator   -> name yang dibuat dari <sequence-generator name="customer_generator"
 *      Sequence generator digunakan jika kita menggunakan sequence di postgre dan menentukan nama sequence yang sudah ada sebelumnya
 * */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_balance")
    private Integer balance;

    // Cascade : Menentukan bagaimana tindakan pada entitas utama (User) akan mempengaruhi entitas (UserProfile)
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_profile_id", unique = true)
    private UserProfile userProfile;

    public User(String id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public User() {
        // ini wajib ada, supaya nanti ORM bisa membuat object tanpa parameter.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
