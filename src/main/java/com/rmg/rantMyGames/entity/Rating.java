package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ratings")
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RatingId")
    private Long ratingId;

    @Column(name = "RatingValue")
    private Integer ratingValue;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User qualifyingUser;

    @ManyToOne()
    @JoinColumn(name = "GameId")
    private Game gameRated;
}
