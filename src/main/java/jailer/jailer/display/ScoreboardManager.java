package jailer.jailer.display;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;

import static jailer.jailer.display.Formatter.*;

public class ScoreboardManager implements Listener {

    private HashMap<Player, FastBoard> scoreboards;


    public ScoreboardManager() {
        this.scoreboards = new HashMap<>();
    }

    public void reloadScoreboard(Player player) {
        FastBoard scoreboard = scoreboards.get(player);

        scoreboard.updateTitle(color("<#2b95ff><bold>SERVER!!!"));

        ArrayList<String> lines = new ArrayList<>();

        lines.add("test");
        lines.add("test");





        scoreboard.updateLines(lines);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        scoreboards.put(player, new FastBoard(player));
        reloadScoreboard(player);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        scoreboards.remove(event.getPlayer()).delete();

    }

}
