package jailer.jailer.blocks.commands;

import jailer.jailer.item.items.admin.MineWand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if (args.length < 1) return false;
        if (args[0].equalsIgnoreCase("wand")) {
            player.getInventory().addItem(new MineWand().toItemStack());
        }

        return false;
    }
}
