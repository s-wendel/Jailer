package seafront.seafront.data.listener;

import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!Seafront.getInstance().playerData.hasData(player)) {
            Seafront.getInstance().playerData.putData(player, new PlayerData(player));
        } else {
            Seafront.getInstance().playerData.loadData(player);
        }

        PlayerData playerData = Seafront.getInstance().playerData.getData(player);
        playerData.setPlayer(player);
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!Seafront.getInstance().playerData.hasCachedData(player)) return;
        Seafront.getInstance().playerData.saveData(player);
        Seafront.getInstance().playerData.removeCachedData(player);
    }
}
