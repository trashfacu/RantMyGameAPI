package com.rmg.rantMyGames.repository;

import com.rmg.rantMyGames.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameRepository extends JpaRepository <Game, Integer> {
    boolean existsByGameTitle(String gameTitle);
}
