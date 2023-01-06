package jailer.jailer.blocks;

import org.bukkit.Location;

public class BrokenBlock {
    public final Location location;
    public int health;

    public int oldAnimation;

    public BrokenBlock(Location location, int health) {
        this.location = location;
        this.oldAnimation = 0;
        this.health = health;
    }
}
