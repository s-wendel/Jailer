package seafront.seafront.data.listener;

import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import seafront.seafront.item.SeafrontEquipmentItem;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontItemFactory;
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
        PlayerData playerData = Seafront.getInstance().playerData.getData(player);

        if(item == null) {
            playerData.setTool(null);
            return;
        }

        SeafrontItem jailerItem = SeafrontItemFactory.jailerItemFromItemStack(item);

        if (jailerItem instanceof SeafrontEquipmentItem) {
            playerData.setTool( (SeafrontEquipmentItem) jailerItem);
        }
    }

}
