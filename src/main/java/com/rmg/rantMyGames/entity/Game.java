package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Games")
@Getter @Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="game_id")
    private Long gameId;
    @Column(name = "name")
    private String gameTitle;
    @Column(name = "description")
    private String gameDescription;
    @Column(name = "release_date")
    private LocalDate gameReleaseDate;
    @Column(name = "developer")
    private String gameDeveloper;
    @Column(name = "publisher")
    private String gamePublisher;

    @OneToMany(mappedBy = "gameRated")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "gameBelonging")
    private List<Review> reviews;

}
