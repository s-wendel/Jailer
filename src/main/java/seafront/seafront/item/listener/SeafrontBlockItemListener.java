package seafront.seafront.item.listener;

import de.tr7zw.nbtapi.NBTBlock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import seafront.seafront.item.SeafrontAbilityItem;
import seafront.seafront.item.SeafrontBlockItem;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontItemFactory;

public class SeafrontBlockItemListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType().equals(Material.AIR)) return;

        SeafrontItem jailerItem = SeafrontItemFactory.jailerItemFromItemStack(item);

        if (jailerItem instanceof SeafrontBlockItem blockItem) {
            NBTBlock nbt = new NBTBlock(event.getBlock());
            nbt.getData().setString("block_name", blockItem.getJailerBlock().toString());
        }

    }
}
