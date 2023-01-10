package seafront.seafront.item.listener;

import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import seafront.seafront.item.AbilityAction;
import seafront.seafront.item.AbilityActionType;
import seafront.seafront.item.SeafrontAbilityItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityActionRightClickListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {

        if(!event.getAction().isRightClick()) {
            return;
        }

        Player player = event.getPlayer();
        PlayerData playerData = Seafront.getInstance().playerData.getData(player);
        SeafrontAbilityItem tool = playerData.getTool();

        if(tool == null) {
            return;
        }

        tool.abilityTrigger( new AbilityAction(AbilityActionType.RIGHT_CLICK, event, player));

    }

}
