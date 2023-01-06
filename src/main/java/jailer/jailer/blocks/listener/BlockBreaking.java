package jailer.jailer.blocks.listener;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTItem;
import jailer.jailer.Jailer;
import jailer.jailer.blocks.BrokenBlock;
import jailer.jailer.blocks.JailerBlock;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.JailerStat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockSupport;
import org.bukkit.block.data.BlockData;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import static com.comphenix.protocol.PacketType.Play.Server.BLOCK_BREAK_ANIMATION;

public class BlockBreaking implements Listener {

    private static HashMap<Location, BrokenBlock> breakingBlocks = new HashMap<>();
    private static JailerBlock defaultBlock = JailerBlock.STONE;


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
        PlayerData playerData = Jailer.getInstance().playerData.getData(player);

        Block block = player.getTargetBlock(Set.of(Material.AIR, Material.WATER, Material.LAVA), 5);
        Location blockPosition = block.getLocation();

        if (!breakingBlocks.containsKey(blockPosition)) return;

        BrokenBlock brokenBlock = breakingBlocks.get(blockPosition);

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        double miningSpeed = Jailer.getInstance().playerData.getData(player).getStatValue(JailerStat.MINING_SPEED);

        NBTBlock nbtBlock = new NBTBlock(block);


        JailerBlock jailerblock = defaultBlock;
        if (nbtBlock.getData().hasTag("block_name")) jailerblock = JailerBlock.valueOf(nbtBlock.getData().getString("block_name"));

        int progress = brokenBlock.health - (int) miningSpeed;


        if (progress <= 0) {
            player.playSound(event.getPlayer().getLocation(), jailerblock.sound, 1f, 1f);
            block.setType(Material.AIR);
            nbtBlock.getData().removeKey("block_name");
            breakingBlocks.remove(blockPosition);
            player.getInventory().addItem(jailerblock.lootTable.roll().item.toItemStack());
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
