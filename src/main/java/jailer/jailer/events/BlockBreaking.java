package jailer.jailer.events;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import jailer.jailer.blocks.JailerBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.inventory.ItemStack;
import com.comphenix.protocol.events.PacketContainer;

import java.util.HashMap;
import java.util.Set;

import static com.comphenix.protocol.PacketType.Play.Server.BLOCK_BREAK_ANIMATION;

public class BlockBreaking implements Listener {

    private static HashMap<Location, Integer> breakingBlocks = new HashMap<>();


    @EventHandler
    public void onBlockBreak(BlockDamageEvent event) {

        if (!breakingBlocks.containsKey(event.getBlock().getLocation())) return;

        NBTBlock nbt = new NBTBlock(event.getBlock());
        JailerBlock block = JailerBlock.valueOf(nbt.getData().getOrCreateCompound("JailerData").getString("BlockName"));

        breakingBlocks.put(event.getBlock().getLocation(), block.durability);
    }

    @EventHandler
    public void onBreakingBlock(PlayerAnimationEvent event){
        Player player = event.getPlayer();

        Block block = player.getTargetBlock(Set.of(Material.AIR), 5);
        Location blockPosition = block.getLocation();

        ItemStack itemStack = player.getItemInHand();
        NBTItem nbt = new NBTItem(itemStack);
        NBTBlock nbtBlock = new NBTBlock(block);
        
        Integer miningSpeed = nbt.getCompound("JailerData").getCompound("Stats").getInteger("MiningSpeed");
        JailerBlock jailerblock = JailerBlock.valueOf(nbtBlock.getData().getOrCreateCompound("JailerData").getString("BlockName"));
        int progress = breakingBlocks.get(blockPosition) - miningSpeed;



        if (progress <= 0) {
            // the block is broken
            return;
        }

        double distanceX = blockPosition.getX() - player.getLocation().getX();
        double distanceY = blockPosition.getY() - player.getLocation().getY();
        double distanceZ = blockPosition.getZ() - player.getLocation().getZ();

        if(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ >= 1024.0D) return;

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        PacketContainer packet = manager.createPacket(BLOCK_BREAK_ANIMATION);


        int animationStage = (progress / jailerblock.durability) * 9;
        packet.getIntegers().write(0, event.getPlayer().getEntityId());
        packet.getBlockPositionModifier().write(0, new BlockPosition(blockPosition.toVector()));
        packet.getBytes().write(0, (byte) animationStage);

        breakingBlocks.put(blockPosition, progress);


    }


}
