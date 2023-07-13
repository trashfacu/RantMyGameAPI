package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Game")
@Getter @Setter
public class Game {
    @Id
    @GeneratedValue
    @Column(name = "GameID")
    private Integer gameId;
    @Column(name = "Name")
    private String gameTitle;
    @Column(name = "Description")
    private String gameDescription;
    @Column(name = "ReleaseDate")
    private LocalDate gameReleaseDate;
    @Column(name = "Developer")
    private String gameDeveloper;
    @Column(name = "Publisher")
    private String gamePublisher;

    @OneToMany(mappedBy = "gameRated")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "gameBelonging")
    private List<Review> reviews;

}
