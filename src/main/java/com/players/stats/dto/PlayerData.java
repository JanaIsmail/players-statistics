package com.players.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerData {

    int rank;
    int points;
    double weight;
    double height;
    int age;
    List<Integer> last;
}
