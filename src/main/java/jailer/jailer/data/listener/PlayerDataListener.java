package jailer.jailer.data.listener;

import jailer.jailer.Jailer;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.JailerItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!Jailer.getInstance().playerData.hasData(player)) {
            Jailer.getInstance().playerData.putData(player, new PlayerData(player));
        } else {
            Jailer.getInstance().playerData.loadData(player);
        }

        PlayerData playerData = Jailer.getInstance().playerData.getData(player);
        playerData.setPlayer(player);
        playerData.setTool(JailerItemFactory.jailerEquipmentItemFromItemStack(player.getInventory().getItemInMainHand()));
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!Jailer.getInstance().playerData.hasCachedData(player)) return;
        Jailer.getInstance().playerData.saveData(player);
        Jailer.getInstance().playerData.removeCachedData(player);
    }
}
