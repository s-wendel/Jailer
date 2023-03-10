package seafront.seafront.item;

import seafront.seafront.Seafront;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SeafrontStatData {

    /**
     * This class is used for /stats command so it can show where all your stats come from
     */

    private Player player;
    private String name;
    private Material icon;
    private String value; // A double, ex 0 or a multiplier ex 1.25x
    private long expires; // in millis, set to -1 for infinite

    public SeafrontStatData(Player player, String name, Material icon, String value, long expires) {
        this.player = player;
        this.name = name;
        this.icon = icon;
        this.value = value;
        this.expires = expires;

        if(expires != -1) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(Seafront.getInstance(), task -> {

                Seafront.getInstance().playerData.getData(player).updateCache();
                // Difference between now and expiration converted to ticks, 20 ticks per second, so X / 50
            }, Math.abs(expires - System.currentTimeMillis()) / 50);
        }

    }

    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }

    public String getValue() {
        return value;
    }

    public boolean hasExpired() {
        if(expires == -1) {
            return false;
        }
        return System.currentTimeMillis() >= expires;
    }

    public long getExpiration() {
        return expires;
    }

}
