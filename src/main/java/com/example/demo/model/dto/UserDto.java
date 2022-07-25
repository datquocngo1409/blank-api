package com.example.demo.model.dto;

import com.example.demo.model.User;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {
    private Long id;
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
    private boolean isAms;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.name = user.getName();
        this.age = user.getAge();
        this.type = user.getType();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.idNumber = user.getIdNumber();
        this.avatarUrl = user.getAvatarUrl();
        this.token = user.getToken();
        this.isAms = user.isAms();
    }
}
