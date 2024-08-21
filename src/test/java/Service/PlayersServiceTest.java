package Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.players.stats.dto.Player;
import com.players.stats.dto.Players;
import com.players.stats.dto.PlayersStatistics;
import com.players.stats.exception.PlayerNotFoundException;
import com.players.stats.service.PlayersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayersServiceTest {

    private static PlayersService playersService;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        Players players = Players.builder().build();
        try {
            players = mapper.readValue(new File("src/test/resources/players.json"), Players.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        playersService = new PlayersService(players);
    }

    @Test
    void get_all_players_sorts_players_by_rank() {
        Players players = playersService.getAllPlayers();

        Player topPlayer = players.getPlayers().get(0);
        Player secondPlayer = players.getPlayers().get(1);
        Player thirdPlayer = players.getPlayers().get(2);

        assertEquals(3, players.getPlayers().size(), 3);
        assertEquals("second", topPlayer.getFirstname());
        assertEquals("third", secondPlayer.getFirstname());
        assertEquals("first", thirdPlayer.getFirstname());
    }

    @Test
    void get_player_by_id_returns_correct_player() {
        Player player = playersService.getPlayerById(2);

        assertEquals("second", player.getFirstname());
    }

    @Test
    void get_player_by_id_throws_exception_when_player_not_found() {
        assertThrows(PlayerNotFoundException.class, () -> {
            playersService.getPlayerById(4);
        });
    }

    @Test
    void get_stats_returns_correct_results() {
        PlayersStatistics stats = playersService.getStats();

        assertEquals("CTR2", stats.getBestWinRatio());
        assertEquals(String.format("%.3f", 26.969), String.format("%.3f", stats.getAverageBMI()));
        assertEquals(170, stats.getHeightMedian());
    }
}
