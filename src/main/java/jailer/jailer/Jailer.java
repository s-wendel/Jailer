package jailer.jailer;

import jailer.jailer.blocks.Mine;
import jailer.jailer.blocks.commands.MineCommand;
import jailer.jailer.data.PlayerData;
import jailer.jailer.data.listener.PlayerDataListener;
import jailer.jailer.data.listener.PlayerToolAutoEquipListener;
import jailer.jailer.data.serialize.GsonSerializer;
import jailer.jailer.data.serialize.MineSerializer;
import jailer.jailer.data.serialize.PlayerSerializer;
import jailer.jailer.data.storage.FileStorageProvider;
import jailer.jailer.data.storage.StorageManager;
import jailer.jailer.blocks.listener.BlockBreaking;
import jailer.jailer.item.command.JailerItemCommand;
import jailer.jailer.item.listener.AbilityActionLeftClickListener;
import jailer.jailer.item.listener.AbilityActionRightClickListener;
import jailer.jailer.item.listener.PlayerToolSwitchListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Jailer extends JavaPlugin {

    private static Jailer instance;

    public final StorageManager<Player, PlayerData> playerData =
            new StorageManager<Player, PlayerData>(
                    new FileStorageProvider<Player, PlayerData>(
                            new PlayerSerializer(),
                            new GsonSerializer<PlayerData>(),
                            getDataFolder())
            );

    public final StorageManager<String, Mine> mineManager =
            new StorageManager<>(
                    new FileStorageProvider<String, Mine>(
                            new GsonSerializer<>(),
                            new MineSerializer(),
                            new File(getDataFolder().getPath() + "/structures")
                    )
            );

    @Override
    public void onEnable() {
        instance = this;

        getCommand("items").setExecutor(new JailerItemCommand());
        getCommand("mine").setExecutor(new MineCommand());

        getServer().getPluginManager().registerEvents(new BlockBreaking(), this);
        getServer().getPluginManager().registerEvents(new PlayerToolAutoEquipListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToolSwitchListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDataListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityActionRightClickListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityActionLeftClickListener(), this);

        for (String string : mineManager.keys()) {
            mineManager.loadData(string);
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        for (Player player : playerData.cachedKeys()) {
            playerData.saveData(player);
        }
        for (String string : mineManager.cachedKeys()) {
            mineManager.saveData(string);
        }
    }

    public static Jailer getInstance() {
        return instance;
    }

}
