package jailer.jailer.item.listener;

import jailer.jailer.Jailer;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.AbilityAction;
import jailer.jailer.item.JailerAbilityItem;
import jailer.jailer.item.JailerItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityActionRightClickListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {

        if(!event.getAction().isRightClick()) {
            return;
        }

        Player player = event.getPlayer();
        PlayerData playerData = Jailer.getInstance().playerData.getData(player);
        JailerAbilityItem tool = playerData.getTool();

        if(tool == null) {
            return;
        }

        tool.abilityTrigger(player, AbilityAction.RIGHT_CLICK);

    }

}
