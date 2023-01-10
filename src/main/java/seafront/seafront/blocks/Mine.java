package seafront.seafront.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Mine {
    private static final StructureManager STRUCTURE_MANAGER = Bukkit.getStructureManager();

    public final String name;

    private transient Structure structure = STRUCTURE_MANAGER.createStructure();
    private String saveFilePath;
    private Vector spawnLocation;
    private String world;

    public Mine(String name, File saveFile) {
        this.saveFilePath = saveFile.getPath();
        this.name = name;
    }

    public void setStructure(Location location, Location location2) {
        structure.fill(location, location2, false);
        spawnLocation = location.toVector();
        this.world = location.getWorld().toString();

    }

    public void placeStructure() {
        World world = Bukkit.getWorld(this.world);
        if (world == null) return;
        structure.place(spawnLocation.toLocation(world), false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
    }

    public void load() {
        try {
            structure = STRUCTURE_MANAGER.loadStructure(new File(saveFilePath));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void save() {
        try {
            STRUCTURE_MANAGER.saveStructure(new File(saveFilePath), structure);
        } catch (Exception e) { e.printStackTrace(); }

    }


}
