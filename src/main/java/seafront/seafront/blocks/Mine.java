package seafront.seafront.blocks;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTContainer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.Vector;
import seafront.seafront.blocks.listener.BlockBreaking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Mine {
    private ArrayList<MineBlock> structure = new ArrayList<>();
    private String world;



    public Mine() {
    }

    public void setStructure(Location location, Location location2) {

        double minX = Math.min(location.getX(), location2.getX());
        double minZ = Math.min(location.getZ(), location2.getZ());
        double minY = Math.min(location.getY(), location2.getY());
        double maxX = Math.max(location.getX(), location2.getX());
        double maxZ = Math.max(location.getZ(), location2.getZ());
        double maxY = Math.max(location.getY(), location2.getY());

        structure = new ArrayList<>();

        World world = location.getWorld();
        for (int x = (int) minX; x < (int) maxX; x++) {
            for (int z = (int) minZ; z < (int) maxZ; z++) {
                for (int y = (int) minY; y < (int) maxY; y++) {
                    Block block = world.getBlockAt(x,y,z);
                    if (block.getType().equals(Material.AIR)) continue;
                    NBTBlock nbt = new NBTBlock(block);
                    JailerBlock jailerBlock = (nbt.getData().hasTag("block_name") ? JailerBlock.valueOf(nbt.getData().getString("block_name")) : JailerBlock.UNBREAKABLE);
                    structure.add(new MineBlock(nbt.getData().asNBTString() ,block.getType(), block.getLocation().toVector()));

                }
            }
        }
        this.world = location.getWorld().getName();

    }

    public void placeStructure() {
        World world = Bukkit.getWorld(this.world);
        if (world == null) return;
        structure.forEach((mineBlock) -> {
            Block block = mineBlock.getLocation().toLocation(world).getBlock();
            block.setType(mineBlock.getMaterial());
            NBTBlock nbt = new NBTBlock(block);
            nbt.getData().mergeCompound(new NBTContainer(mineBlock.getNBT()));
        });
    }



}
