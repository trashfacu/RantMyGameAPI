package com.rmg.rantMyGames.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Reviews")
@Getter @Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "text_review")
    private String textReview;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game gameBelonging;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userWhoWroteIt;

}
