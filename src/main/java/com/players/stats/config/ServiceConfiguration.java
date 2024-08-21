package com.players.stats.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.players.stats.dto.Players;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class ServiceConfiguration {

    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public Players readPlayersFile() {
        // ideally we would have a database
        Players players = null;
        try {
            players = mapper.readValue(new File("src/main/resources/players.json"), Players.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }
}
