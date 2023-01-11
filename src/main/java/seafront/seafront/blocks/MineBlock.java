package seafront.seafront.blocks;

import org.bukkit.Material;
import org.bukkit.util.Vector;

public class MineBlock {

    private Material material;

    private Vector location;

    private String NBT;

    public MineBlock(String NBT, Material material, Vector location) {
        this.material = material;
        this.location = location;
        this.NBT = NBT;
    }

    public Vector getLocation() {
        return location;
    }

    public Material getMaterial() {
        return material;
    }



    public String getNBT() {
        return NBT;
    }
}
