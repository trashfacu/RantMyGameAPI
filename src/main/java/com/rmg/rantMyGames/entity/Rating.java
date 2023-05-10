package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ratings")
@Getter @Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;
    @Column(name = "rating_value")
    private Long ratingValue;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User qualifyingUser;

    @ManyToOne()
    @JoinColumn(name = "game_id")
    private Game gameRated;

}
