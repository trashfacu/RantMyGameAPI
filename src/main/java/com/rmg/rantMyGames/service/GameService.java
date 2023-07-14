package com.rmg.rantMyGames.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmg.rantMyGames.entity.Game;
import com.rmg.rantMyGames.model.GameDTO;
import com.rmg.rantMyGames.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService implements ICRUDService<GameDTO, Game> {

    private static final Logger logger = Logger.getLogger(GameService.class);

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void create(GameDTO gameDTO) throws Exception {
        // Validate game cannot be null
        if (gameDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game cannot be null");
        }
        // Validate release date
        if (gameDTO.getGameReleaseDate().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid release date. Release date cannot be in the future.");
        }
        // Check for unique game title
        if (gameRepository.existsByGameTitle(gameDTO.getGameTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game title must be unique. Another game with the same title already exists.");
        }
        try {
            // Convert GameDTO to Game Entity
            Game game = mapper.convertValue(gameDTO, Game.class);
            gameRepository.save(game);
        } catch (Exception e) {
            // Log the exception for troubleshooting
            logger.error("An error occurred while saving the game", e);
            throw e;
        }
    }

    @Override
    public GameDTO read(Integer id) throws Exception {
        // Validate that the id is not null or less than zero
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        // Check if the game exists in the database
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return mapper.convertValue(game.get(), GameDTO.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        // Validate that the id is not null or less than zero
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        // Check if the game exists
        if (!gameRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameRepository.deleteById(id);
    }

    @Override
    public void update(GameDTO gameDTO) throws Exception {
        // Validate that the DTO is not null
        if (gameDTO == null) {
            throw new IllegalArgumentException("Game DTO cannot be null");
        }
        // Check if the game id is valid
        Integer gameId = gameDTO.getId();
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        // Check if the game exists in the database
        if (!gameRepository.existsById(gameId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        // Convert GameDTO to Game entity and set the game ID explicitly
        Game game = mapper.convertValue(gameDTO, Game.class);
        game.setGameId(gameId);
        gameRepository.save(game);
    }

    @Override
    public List<GameDTO> getAll() {
        List<Game> gameList = gameRepository.findAll();
        List<GameDTO> gameDTOList = new ArrayList<>();
        for (Game game : gameList) {
            gameDTOList.add(mapper.convertValue(game, GameDTO.class));
        }
        return gameDTOList;
    }
}
