package com.rmg.rantMyGames.controller;

import com.rmg.rantMyGames.entity.Game;
import com.rmg.rantMyGames.model.GameDTO;
import com.rmg.rantMyGames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<String> addGame(@RequestBody GameDTO gameDTO) throws Exception {
        gameService.create(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Game created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Integer id) throws Exception {
        GameDTO gameDTO = gameService.read(id);
        return ResponseEntity.ok(gameDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@PathVariable Integer id, @RequestBody GameDTO gameDTO) throws Exception {
        gameDTO.setId(id); // Ensure the provided ID is set in the DTO
        gameService.update(gameDTO);
        return ResponseEntity.ok("Game updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Integer id) throws Exception {
        gameService.delete(id);
        return ResponseEntity.ok("Game deleted");
    }

    @GetMapping("/list")
    public ResponseEntity<List<GameDTO>> listGames() {
        List<GameDTO> gameDTOList = gameService.getAll();
        return ResponseEntity.ok(gameDTOList);
    }

    // Exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Handle and return appropriate error response based on the exception
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error";
        if (e instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            message = e.getMessage();
        }
        return ResponseEntity.status(status).body(message);
    }
}
