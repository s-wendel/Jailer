package seafront.seafront.blocks.commands;

import seafront.seafront.Seafront;
import seafront.seafront.blocks.Mine;
import seafront.seafront.data.PlayerData;
import seafront.seafront.item.items.admin.MineWand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if (args.length < 1) return false;

        PlayerData playerData = Seafront.getInstance().playerData.getData(player);

        switch (args[0].toLowerCase()) {
            case "wand" -> player.getInventory().addItem(new MineWand().toItemStack());
            case "create" -> {

                if (args.length < 2) return false;
                if (!playerData.locationMap.containsKey("mine1") || !playerData.locationMap.containsKey("mine2")) return false;


                Mine mine = new Mine();
                mine.setStructure(playerData.locationMap.get("mine1").clone(),playerData.locationMap.get("mine2").clone());

                Seafront.getInstance().mineManager.putData(args[1], mine);
                Seafront.getInstance().mineManager.saveData(args[1]);

            }
            case "redefine" -> {

                if (args.length < 2) return false;
                if (!playerData.locationMap.containsKey("mine1") || !playerData.locationMap.containsKey("mine2")) return false;

                Mine mine = Seafront.getInstance().mineManager.getData(args[1]);

                if (mine == null) return false;

                mine.setStructure(playerData.locationMap.get("mine1").clone(),playerData.locationMap.get("mine2").clone());
            }
            case "reset" -> {
                if (args.length < 2) return false;

                Mine mine = Seafront.getInstance().mineManager.getData(args[1]);

                if (mine == null) return false;

                mine.placeStructure();
            }

        }


        return false;
    }
}
