package com.rmg.rantMyGames.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class GameDTO {
    private Long id;
    private String gameTitle;
    private String gameDescription;
    private LocalDate gameReleaseDate;
    private String gameDeveloper;
    private String gamePublisher;

}
