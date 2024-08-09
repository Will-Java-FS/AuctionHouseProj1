package com.revature.auction.models;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
public class User {
    @Getter
    @Id //makes this a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",updatable = false)
    private int user_id;

    @Getter
    @Setter
    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "isAdmin")
    private boolean isAdmin = false;

    @Getter
    @Setter
    @Column(name = "userImage")
    private String userImage;


    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", userImage='" + userImage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUser_id() == user.getUser_id() && Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getUsername());
    }
}