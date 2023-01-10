package jailer.jailer.item.items.admin;

import jailer.jailer.Jailer;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class MineWand  extends JailerAbilityItem {

    public MineWand() {
        super(
                "Mine Wand",
                Material.BLAZE_ROD,
                List.of("Uhmm... I think you do magic with this"),
                JailerRarity.LEGENDARY,
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
                PlayerData playerData = Jailer.getInstance().playerData.getData(action.player);
                PlayerInteractEvent event = (PlayerInteractEvent)  action.event;
                if (!event.hasBlock()) return;
                playerData.locationMap.put("mine2", event.getClickedBlock().getLocation());
            }
            case RIGHT_CLICK -> {
                PlayerData playerData = Jailer.getInstance().playerData.getData(action.player);
                PlayerInteractEvent event = (PlayerInteractEvent)  action.event;
                if (!event.hasBlock()) return;
                playerData.locationMap.put("mine1", event.getClickedBlock().getLocation());

            }
        }
    }
}