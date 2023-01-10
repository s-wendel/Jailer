package jailer.jailer.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Mine {
    private static final StructureManager STRUCTURE_MANAGER = Bukkit.getStructureManager();

    public final String name;

    private transient Structure structure = STRUCTURE_MANAGER.createStructure();
    private File saveFile;
    private Location spawnLocation;

    public Mine(String name, File saveFile) {
        this.saveFile = saveFile;
        this.name = name;
    }

    public void setStructure(Location location, Location location2) {
        structure.fill(location, location2, false);
        spawnLocation = location;

    }

    public void placeStructure() {
        structure.place(spawnLocation, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
    }

    public void load() {
        try {
            structure = STRUCTURE_MANAGER.loadStructure(saveFile);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void save() {
        try {
            STRUCTURE_MANAGER.saveStructure(saveFile, structure);
        } catch (Exception e) { e.printStackTrace(); }

    }


}
