package com.rmg.rantMyGames.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmg.rantMyGames.entity.Game;
import com.rmg.rantMyGames.model.GameDTO;
import com.rmg.rantMyGames.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameService implements ICRUDService <GameDTO, Game>{

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void create(GameDTO gameDTO) throws Exception {
        //Validate game cannot be null
        if (gameDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game cannot be null");
        }
        //Validate release date
        if(gameDTO.getGameReleaseDate().isAfter(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid release date. Release date cannot be in the future.");
        }
        // Check for unique game title
        if (gameRepository.existsByGameTitle(gameDTO.getGameTitle())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Game title must be unique. Another game with that same title already exist.");
        }
        // Convert GameDTO to Game Entity
        Game game = mapper.convertValue(gameDTO,Game.class);
        gameRepository.save(game);
    }

    @Override
    public GameDTO read(Long id) throws Exception {
        //Validat that the id is not null or less that cero
        if (id == null|| id <= 0){
            throw new IllegalArgumentException("Invalid game ID");
        }
        //Check if the game exist in the database
        Optional<Game> game = gameRepository.findById(id);
        if(game.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Game not found");
        }
        GameDTO gameDTO = mapper.convertValue(game.get(),GameDTO.class);
        return gameDTO;
    }

    @Override
    public void delete(Long id) throws Exception {
        //Validat that the id is not null or less that cero
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        //Check if the game exist
        if (!gameRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameRepository.deleteById(id);
    }

    @Override
    public void update(GameDTO gameDTO) throws Exception {
        //Validate that the DTO is not null
        if (gameDTO == null) {
            throw new IllegalArgumentException("Game DTO cannot be null");
        }
        //Check if the game id is valid
        Long gameId = gameDTO.getId();
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        //Check if exist in the database
        if (!gameRepository.existsById(gameId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }

        Game game = mapper.convertValue(gameDTO, Game.class);
        gameRepository.save(game);
    }


    @Override
    //Ths retrieves all game from the db, then convers each game in gameDTO, add them to the list and return said list.
    public List<GameDTO> getAll() {
        List<Game> gameList = gameRepository.findAll();
        List<GameDTO> gameDTOList = new ArrayList<>();
        for (Game game : gameList){
            gameDTOList.add(mapper.convertValue(game,GameDTO.class));
        }
        return gameDTOList;
    }
}
