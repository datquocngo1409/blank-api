package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tbl_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    private String name;
    private Long age;
    private String type;
    private String address;
    private String phone;
    private String email;
    private String idNumber;
    private String avatarUrl;
    private String token;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
