package jailer.jailer.blocks.listener;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import jailer.jailer.blocks.BrokenBlock;
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
import java.util.Random;
import java.util.Set;

import static com.comphenix.protocol.PacketType.Play.Server.BLOCK_BREAK_ANIMATION;

public class BlockBreaking implements Listener {

    private static HashMap<Location, BrokenBlock> breakingBlocks = new HashMap<>();
    private static JailerBlock defaultBlock = JailerBlock.UNBREAKABLE;


    @EventHandler
    public void onBlockBreak(BlockDamageEvent event) {

        if (breakingBlocks.containsKey(event.getBlock().getLocation())) return;

        NBTBlock nbt = new NBTBlock(event.getBlock());
        JailerBlock block = defaultBlock;
        if (nbt.getData().hasTag("block_name")) block = JailerBlock.valueOf(nbt.getData().getString("block_name"));

        breakingBlocks.put(event.getBlock().getLocation(), new BrokenBlock(event.getBlock().getLocation(), block.durability));
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

        BrokenBlock brokenBlock = breakingBlocks.get(blockPosition);

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


        JailerBlock jailerblock = defaultBlock;
        if (nbtBlock.getData().hasTag("block_name")) jailerblock = JailerBlock.valueOf(nbtBlock.getData().getString("block_name"));

        int progress = brokenBlock.health - miningSpeed;


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

        double animationStage = Math.floor(( (double) (jailerblock.durability - progress) / (double) jailerblock.durability) * 9);

        brokenBlock.health = progress;


        if (brokenBlock.oldAnimation == (int) animationStage) return;
        brokenBlock.oldAnimation = (int) animationStage;

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        PacketContainer packet = manager.createPacket(BLOCK_BREAK_ANIMATION);




        packet.getIntegers().write(0, new Random().nextInt(99999999));
        packet.getBlockPositionModifier().write(0, new BlockPosition(blockPosition.toVector()));
        packet.getIntegers().write(1, (int) animationStage);



        try {
            manager.sendServerPacket(event.getPlayer(), packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
