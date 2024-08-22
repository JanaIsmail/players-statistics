package com.players.stats.service;

import com.players.stats.dto.Player;
import com.players.stats.dto.Players;
import com.players.stats.dto.PlayersStatistics;
import com.players.stats.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PlayersService {

    private final List<Player> playersList;

    @Autowired
    public PlayersService(Players players) {
        this.playersList = (null != players) ? players.getPlayers() : new ArrayList<>();
    }

    public Players getAllPlayers() {
        List<Player> sortedPlayers = playersList.stream().sorted(Comparator.comparingInt(player -> player.getData().getRank())).toList();
        return Players.builder().players(sortedPlayers).build();
    }

    public Player getPlayerById(int playerId) {
        return playersList.stream().filter(player -> player.getId() == playerId).findFirst().orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public PlayersStatistics getStats() {
        String bestWinRatio = computeBestWinRatio(playersList);
        double averageBMI = computeAverageBMI(playersList);
        double heightMedian = computeHeightMedian(playersList);
        return PlayersStatistics.builder().bestWinRatio(bestWinRatio).averageBMI(averageBMI).heightMedian(heightMedian).build();
    }

    private String computeBestWinRatio(List<Player> playersList) {
        Map<String, List<Integer>> lastWinByCountry = new HashMap<>();
        playersList.forEach(player -> {
            lastWinByCountry.computeIfAbsent(player.getCountry().getCode(), key -> new ArrayList<>()).addAll(player.getData().getLast());
        });
        Map<String, Double> winRatioByCountry = new HashMap<>();
        lastWinByCountry.forEach((country, last) -> {
            double ratio = last.stream().mapToDouble(val -> val).average().orElse(0.0);
            winRatioByCountry.put(country, ratio);
        });
        return Collections.max(winRatioByCountry.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private double computeAverageBMI(List<Player> playersList) {
        return playersList.stream().mapToDouble(this::computePlayerBMI).average().orElse(0);
    }

    private double computePlayerBMI(Player player) {
        return (player.getData().getWeight() / 1000.0) / Math.pow(player.getData().getHeight() / 100.0, 2);
    }

    private double computeHeightMedian(List<Player> playersList) {
        List<Double> heights = playersList.stream().map(player -> player.getData().getHeight()).sorted().toList();
        int totalNumber = heights.size();
        if (totalNumber % 2 != 0) return heights.get((totalNumber - 1) / 2);
        int middleIndex = totalNumber / 2;
        return (heights.get(middleIndex) + heights.get(middleIndex - 1)) / 2;
    }
}
