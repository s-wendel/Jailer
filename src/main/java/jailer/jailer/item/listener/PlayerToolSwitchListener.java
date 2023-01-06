package jailer.jailer.item.listener;

import jailer.jailer.Jailer;
import jailer.jailer.data.PlayerData;
import jailer.jailer.data.storage.StorageManager;
import jailer.jailer.item.JailerItemFactory;
import jailer.jailer.item.JailerStat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerToolSwitchListener implements Listener {

    @EventHandler
    public void toolSwap(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        PlayerData playerData = Jailer.getInstance().playerData.getData(player);

        playerData.setTool(JailerItemFactory.jailerEquipmentItemFromItemStack(player.getInventory().getItem(event.getNewSlot())));
    }

}
