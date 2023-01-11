package seafront.seafront.item.items.admin;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import seafront.seafront.display.Formatter;
import seafront.seafront.item.AbilityAction;
import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontAbilityItem;
import seafront.seafront.item.SeafrontRarity;

import java.util.List;

public class MineWand  extends SeafrontAbilityItem {

    public MineWand() {
        super(
                "Mine Wand",
                Material.BLAZE_ROD,
                List.of("Uhmm... I think you do magic with this"),
                SeafrontRarity.LEGENDARY,
                ItemType.PICKAXE,
                -1,
                "Select Coordinates",
                List.of("Right or Left click to select coords for mines")
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {
        switch(action.type) {
            case LEFT_CLICK -> {
                PlayerData playerData = Seafront.getInstance().playerData.getData(action.player);
                PlayerInteractEvent event = (PlayerInteractEvent)  action.event;
                if (!event.hasBlock()) return;
                playerData.locationMap.put("mine2", event.getClickedBlock().getLocation());
                Location loc = event.getClickedBlock().getLocation();
                action.player.sendMessage(Formatter.text("<green>Position 2 for mines set at ( X: " + loc.getX() + " Y: " + loc.getY() + " Z: "+ loc.getZ() + " )" ));
                action.player.playSound(
                        Sound.sound()
                                .type(Key.key("block.note_block.bell"))
                                .build()
                );
            }
            case RIGHT_CLICK -> {
                PlayerData playerData = Seafront.getInstance().playerData.getData(action.player);
                PlayerInteractEvent event = (PlayerInteractEvent)  action.event;
                if (!event.hasBlock()) return;
                playerData.locationMap.put("mine1", event.getClickedBlock().getLocation());
                event.setCancelled(true);
                Location loc = event.getClickedBlock().getLocation();
                action.player.sendMessage(Formatter.text("<green>Position 1 for mines set at ( X: " + loc.getX() + " Y: " + loc.getY() + " Z: "+ loc.getZ() + " )" ));
                action.player.playSound(
                        Sound.sound()
                                .type(Key.key("block.note_block.bell"))
                                .build()
                );
            }
        }
    }
}