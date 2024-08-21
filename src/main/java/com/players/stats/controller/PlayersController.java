package com.players.stats.controller;

import com.players.stats.dto.Player;
import com.players.stats.dto.Players;
import com.players.stats.dto.PlayersStatistics;
import com.players.stats.service.PlayersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class PlayersController {

    private final PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Players> getAllPlayers() {
        log.info("Retrieving all players");
        return ResponseEntity.ok(playersService.getAllPlayers());
    }

    @GetMapping(value = "/players/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayerById(@PathVariable int playerId) {
        log.info("Retrieving player with id {}", playerId);
        return ResponseEntity.ok(playersService.getPlayerById(playerId));
    }

    @GetMapping(value = "/players/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayersStatistics> getStatistics() {
        log.info("Retrieving players statistics");
        return ResponseEntity.ok(playersService.getStats());
    }
}
