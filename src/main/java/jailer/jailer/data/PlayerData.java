package jailer.jailer.data;

import jailer.jailer.item.JailerEquipmentItem;
import org.bukkit.entity.Player;

public class PlayerData {

    private Player player;
    private transient JailerEquipmentItem tool;

    public Player getPlayer() {
        return player;
    }

    public JailerEquipmentItem getTool() {
        return tool;
    }

    public void setTool(JailerEquipmentItem tool) {
        this.tool = tool;
    }

}
