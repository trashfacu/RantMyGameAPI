package com.rmg.rantMyGames.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GameDTO {
    @JsonProperty("GameId")
    private Integer id;
    private String gameTitle;
    private String gameDescription;
    private LocalDate gameReleaseDate;
    private String gameDeveloper;
    private String gamePublisher;

}
