package com.hadiyasa.example_jpa.entity;

public class User {
    private String id;
    private String name;
    private Integer balance;

    public User(String id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public User(){
        // ini wajib ada, supaya nanti ORM bisa membuat object tanpa parameter
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
