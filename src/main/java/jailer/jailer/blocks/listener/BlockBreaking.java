package jailer.jailer.blocks.listener;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import de.tr7zw.nbtapi.NBTBlock;
import jailer.jailer.Jailer;
import jailer.jailer.blocks.JailerBlock;
import jailer.jailer.data.PlayerData;
import jailer.jailer.item.JailerStat;
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

import java.util.Random;
import java.util.Set;

import static com.comphenix.protocol.PacketType.Play.Server.BLOCK_BREAK_ANIMATION;

public class BlockBreaking implements Listener {

    private static JailerBlock defaultBlock = JailerBlock.STONE;


    @EventHandler
    public void onBlockBreak(BlockDamageEvent event) {
        NBTBlock nbt = new NBTBlock(event.getBlock());

        if (nbt.getData().hasTag("block_last_anim")) return;

        JailerBlock block = (nbt.getData().hasTag("block_name") ? JailerBlock.valueOf(nbt.getData().getString("block_name")) : defaultBlock);

        nbt.getData().setInteger("block_damage", block.durability);
        nbt.getData().setInteger("block_last_anim", 10);
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

        NBTBlock nbtBlock = new NBTBlock(block);

        if (!nbtBlock.getData().hasTag("block_last_anim")) return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        double miningSpeed = playerData.getStatValue(JailerStat.MINING_SPEED);




        JailerBlock jailerBlock = (nbtBlock.getData().hasTag("block_name") ? JailerBlock.valueOf(nbtBlock.getData().getString("block_name")) : defaultBlock);


        int progress = nbtBlock.getData().getInteger("block_damage") - (int) miningSpeed;


        if (progress <= 0) {
            player.playSound(event.getPlayer().getLocation(), jailerBlock.sound, 1f, 1f);
            block.setType(Material.AIR);
            nbtBlock.getData().removeKey("block_name");
            nbtBlock.getData().removeKey("block_last_anim");
            nbtBlock.getData().removeKey("block_damage");
            player.getInventory().addItem(jailerBlock.lootTable.roll().item.toItemStack());
            return;
        }


        double distanceX = blockPosition.getX() - player.getLocation().getX();
        double distanceY = blockPosition.getY() - player.getLocation().getY();
        double distanceZ = blockPosition.getZ() - player.getLocation().getZ();

        if(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ >= 1024.0D) return;

        double animationStage = Math.floor(( (double) (jailerBlock.durability - progress) / (double) jailerBlock.durability) * 9);

        nbtBlock.getData().setInteger("block_damage", progress);


        if (nbtBlock.getData().getInteger("block_last_anim") == (int) animationStage) return;
        nbtBlock.getData().setInteger("block_last_anim", (int) animationStage);

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
