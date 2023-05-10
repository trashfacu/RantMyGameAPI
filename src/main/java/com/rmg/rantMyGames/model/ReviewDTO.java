package com.rmg.rantMyGames.model;

import com.rmg.rantMyGames.entity.Game;
import com.rmg.rantMyGames.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDTO {
    private Long id;
    private String textValue;
    private Game gameBeloning;
    private User userWhoWroteIt;

}
