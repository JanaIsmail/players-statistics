package com.players.stats.controller;

import com.players.stats.dto.HomePageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomePageController {

    public HomePageController() {
    }

    @GetMapping("/")
    public ResponseEntity<HomePageData> homePage() {
        return ResponseEntity.ok(HomePageData.builder().message("Welcome to the players statistics application").build());
    }
}
