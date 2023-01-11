package seafront.seafront.blocks.commands;

import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class NBTCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;


        if (args.length < 1) return false;

        switch (args[0].toLowerCase()) {

            case "block" -> {
                Block block = player.getTargetBlock(Set.of(Material.AIR, Material.WATER, Material.LAVA), 10);
                NBTBlock nbt = new NBTBlock(block);
                player.sendMessage(nbt.getData().asNBTString());
                return true;
            }
            case "item" -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                NBTItem nbt = new NBTItem(item);
                player.sendMessage(nbt.asNBTString());
                return true;
            }
            case "entity" -> {
                Entity entity = player.getTargetEntity(10);
                NBTEntity nbt = new NBTEntity(entity);
                player.sendMessage(nbt.asNBTString());
                return true;
            }

        }

        return false;
    }
}
