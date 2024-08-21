package com.players.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersStatistics {

    String bestWinRatio;
    double averageBMI;
    double heightMedian;

}
