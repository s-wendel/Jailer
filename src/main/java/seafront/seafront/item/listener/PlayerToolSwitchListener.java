package seafront.seafront.item.listener;

import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import seafront.seafront.item.SeafrontAbilityItem;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontItemFactory;

public class PlayerToolSwitchListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void toolSwap(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        PlayerData playerData = Seafront.getInstance().playerData.getData(player);

        if(item == null) {
            playerData.setTool(null);
            return;
        }

        SeafrontItem jailerItem = SeafrontItemFactory.jailerItemFromItemStack(item);

        if (jailerItem instanceof SeafrontAbilityItem) {
            playerData.setTool( (SeafrontAbilityItem) jailerItem);
        }

    }

}
