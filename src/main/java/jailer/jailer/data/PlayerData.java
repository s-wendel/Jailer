package jailer.jailer.data;

import jailer.jailer.item.JailerEquipmentItem;
import jailer.jailer.item.JailerStat;
import org.bukkit.entity.Player;

import java.util.Map;

public class PlayerData {

    private transient Player player;
    private transient JailerEquipmentItem tool;
    //private transient Map<JailerStat, M> stats;

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
        for(JailerStat stat : tool.getStats().keySet()) {

        }
    }

    public void setPlayer(Player player) { if (this.player == null) this.player = player; }

}
