package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer UserId;

    @Column(name = "Username")
    private String username;

    @Column(name = "UserPassword")
    private String password;

    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "userWhoWroteIt")
    private List<Review> userReviews;

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    private List<UserFollows> followers;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<UserFollows> following;
}
