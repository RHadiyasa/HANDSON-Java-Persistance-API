package com.hadiyasa.example_jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "m_user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "account_number", unique = true, length = 12)
    private String accountNumber;

    @Column(name = "phone_number", unique = true, length = 14)
    protected String phoneNumber;

    @Column(name = "address", length = 100)
    private String address;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    public UserProfile(){};

    public UserProfile(String id, String accountNumber, String phoneNumber, String address, User user) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
