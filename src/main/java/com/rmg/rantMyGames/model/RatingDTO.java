package com.rmg.rantMyGames.model;
import com.rmg.rantMyGames.entity.Game;
import com.rmg.rantMyGames.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RatingDTO {
    private Long id;
    private Long ratingValue;
    private User qualifyingUser;
    private Game gameRated;
}
