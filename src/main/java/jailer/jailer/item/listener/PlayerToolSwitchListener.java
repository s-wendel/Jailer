package jailer.jailer.item.listener;

import jailer.jailer.Jailer;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.JailerItemFactory;
import jailer.jailer.item.JailerStat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerToolSwitchListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void toolSwap(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItem(event.getNewSlot());

        if(item == null) {
            return;
        }

        PlayerData playerData = Jailer.getInstance().playerData.getData(player);

        playerData.setTool(JailerItemFactory.jailerEquipmentItemFromItemStack(item));
    }

}
