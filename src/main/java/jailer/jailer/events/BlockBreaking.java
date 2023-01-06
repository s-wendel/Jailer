package jailer.jailer.events;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import jailer.jailer.blocks.JailerBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Set;

import static com.comphenix.protocol.PacketType.Play.Server.BLOCK_BREAK_ANIMATION;

public class BlockBreaking implements Listener {

    private static HashMap<Location, Integer> breakingBlocks = new HashMap<>();


    @EventHandler
    public void onBlockBreak(BlockDamageEvent event) {

        if (breakingBlocks.containsKey(event.getBlock().getLocation())) return;

        NBTBlock nbt = new NBTBlock(event.getBlock());
        String blockName = "Stone";
        if (nbt.getData().hasTag("block_name")) blockName = nbt.getData().getString("block_name");

        JailerBlock block = JailerBlock.valueOf(blockName);

        breakingBlocks.put(event.getBlock().getLocation(), block.durability);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE - 10, -1, false, false));
    }

    @EventHandler
    public void onBreakingBlock(PlayerAnimationEvent event){
        Player player = event.getPlayer();

        Block block = player.getTargetBlock(Set.of(Material.AIR, Material.WATER, Material.LAVA), 5);
        Location blockPosition = block.getLocation();
        if (!breakingBlocks.containsKey(blockPosition)) return;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        int miningSpeed = 1;
        if (itemStack.getType() != Material.AIR) {
            NBTItem nbt = new NBTItem(itemStack);
            if (nbt.hasTag("stats")) {
                if (nbt.getCompound("stats").hasTag("MiningSpeed"))
                    miningSpeed = nbt.getCompound("stats").getInteger("MiningSpeed");
            }
        }

        NBTBlock nbtBlock = new NBTBlock(block);
        

        String blockName = "Stone";
        if (nbtBlock.getData().hasTag("block_name")) blockName = nbtBlock.getData().getString("block_name");

        JailerBlock jailerblock = JailerBlock.valueOf(blockName);
        int progress = breakingBlocks.get(blockPosition) - miningSpeed;


        if (progress <= 0) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), jailerblock.sound, 1f, 1f);
            block.breakNaturally();
            breakingBlocks.remove(blockPosition);
            return;
        }

        double distanceX = blockPosition.getX() - player.getLocation().getX();
        double distanceY = blockPosition.getY() - player.getLocation().getY();
        double distanceZ = blockPosition.getZ() - player.getLocation().getZ();

        if(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ >= 1024.0D) return;

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        PacketContainer packet = manager.createPacket(BLOCK_BREAK_ANIMATION);



        double animationStage = Math.floor(( (double) (jailerblock.durability - progress) / (double) jailerblock.durability) * 9);
        packet.getIntegers().write(0, event.getPlayer().getEntityId());
        packet.getBlockPositionModifier().write(0, new BlockPosition(blockPosition.toVector()));
        packet.getIntegers().write(1, (int) animationStage);

        breakingBlocks.put(blockPosition, progress);

        try {
            manager.sendServerPacket(event.getPlayer(), packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}