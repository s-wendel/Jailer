package jailer.jailer.data;

import jailer.jailer.item.JailerEquipmentItem;
import org.bukkit.entity.Player;

public class PlayerData {

    private transient Player player;
    private transient JailerEquipmentItem tool;


    public PlayerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public JailerEquipmentItem getTool() {
        return tool;
    }

    public void setTool(JailerEquipmentItem tool) {
        this.tool = tool;
    }

    public void setPlayer(Player player) { if (this.player == null) this.player = player; }

}
