package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Reviews")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewId")
    private Long reviewId;

    @Column(name = "TextReview")
    private String textReview;

    @ManyToOne
    @JoinColumn(name = "GameId")
    private Game gameBelonging;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User userWhoWroteIt;
}
