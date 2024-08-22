package com.players.stats.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.players.stats.dto.Players;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Slf4j
@Configuration
public class ServiceConfiguration {

    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public Players readPlayersFile() {
        // ideally we would have a database
        Players players = Players.builder().build();
        try {
            players = mapper.readValue(new File("src/main/resources/players.json"), Players.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return players;
    }
}
