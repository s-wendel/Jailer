package jailer.jailer.data.listener;

import jailer.jailer.Jailer;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.JailerEquipmentItem;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerItemFactory;
import jailer.jailer.item.JailerStat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerToolAutoEquipListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();
        PlayerData playerData = Jailer.getInstance().playerData.getData(player);

        if(item == null) {
            playerData.setTool(null);
            return;
        }

        JailerItem jailerItem = JailerItemFactory.jailerItemFromItemStack(item);

        if (jailerItem instanceof JailerEquipmentItem) {
            playerData.setTool( (JailerEquipmentItem) jailerItem);
        }
    }

}
