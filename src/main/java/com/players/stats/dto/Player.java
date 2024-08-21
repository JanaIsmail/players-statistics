package com.players.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    int id;
    String firstname;
    String lastname;
    String shortname;
    String sex;
    Country country;
    String picture;
    PlayerData data;

}
