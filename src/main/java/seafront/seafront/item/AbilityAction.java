package seafront.seafront.item;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class AbilityAction {

    public final AbilityActionType type;
    public final Event event;

    public final Player player;



    public AbilityAction(AbilityActionType type, Event event, Player player) {
        this.player = player;
        this.type = type;
        this.event = event;
    }

}
