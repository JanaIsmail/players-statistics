package Controller;

import com.players.stats.MainApplication;
import com.players.stats.dto.Player;
import com.players.stats.dto.Players;
import com.players.stats.dto.PlayersStatistics;
import com.players.stats.service.PlayersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MainApplication.class)
@AutoConfigureMockMvc
public class PlayersControllerTest {

    @MockBean
    private PlayersService playersService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_get_all_players() throws Exception {
        when(playersService.getAllPlayers()).thenReturn(Players.builder().build());
        String content = mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        verify(playersService).getAllPlayers();
        assertNotNull(content);
    }

    @Test
    void test_get_player_by_id() throws Exception {
        when(playersService.getPlayerById(anyInt())).thenReturn(Player.builder().build());
        String content = mockMvc.perform(get("/api/players/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        verify(playersService).getPlayerById(1);
        assertNotNull(content);
    }

    @Test
    void test_get_players_stats() throws Exception {
        when(playersService.getStats()).thenReturn(PlayersStatistics.builder().build());
        String content = mockMvc.perform(get("/api/players/stats"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        verify(playersService).getStats();
        assertNotNull(content);
    }
}
