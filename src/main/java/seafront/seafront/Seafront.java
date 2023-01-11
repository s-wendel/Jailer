package seafront.seafront;

import com.google.gson.reflect.TypeToken;
import seafront.seafront.blocks.Mine;
import seafront.seafront.blocks.commands.MineCommand;
import seafront.seafront.data.PlayerData;
import seafront.seafront.data.listener.PlayerDataListener;
import seafront.seafront.data.listener.PlayerToolAutoEquipListener;
import seafront.seafront.data.serialize.GsonSerializer;
import seafront.seafront.data.serialize.PlayerSerializer;
import seafront.seafront.data.serialize.StringSerializer;
import seafront.seafront.data.storage.FileStorageProvider;
import seafront.seafront.data.storage.StorageManager;
import seafront.seafront.blocks.listener.BlockBreaking;
import seafront.seafront.item.command.SeafrontItemCommand;
import seafront.seafront.item.listener.AbilityActionLeftClickListener;
import seafront.seafront.item.listener.AbilityActionRightClickListener;
import seafront.seafront.item.listener.PlayerToolSwitchListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import seafront.seafront.item.listener.SeafrontBlockItemListener;

import java.io.File;

public final class Seafront extends JavaPlugin {

    private static Seafront instance;

    public final StorageManager<Player, PlayerData> playerData =
            new StorageManager<Player, PlayerData>(
                    new FileStorageProvider<Player, PlayerData>(
                            new PlayerSerializer(),
                            new GsonSerializer<PlayerData>(new TypeToken<PlayerData>(){}),
                            getDataFolder())
            );

    public final StorageManager<String, Mine> mineManager =
            new StorageManager<>(
                    new FileStorageProvider<String, Mine>(
                            new StringSerializer(),
                            new GsonSerializer<>(new TypeToken<Mine>(){}),
                            new File(getDataFolder().getPath() + "/structures")
                    )
            );

    @Override
    public void onEnable() {
        instance = this;

        getCommand("items").setExecutor(new SeafrontItemCommand());
        getCommand("mine").setExecutor(new MineCommand());

        getServer().getPluginManager().registerEvents(new BlockBreaking(), this);
        getServer().getPluginManager().registerEvents(new PlayerToolAutoEquipListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToolSwitchListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDataListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityActionRightClickListener(), this);
        getServer().getPluginManager().registerEvents(new AbilityActionLeftClickListener(), this);
        getServer().getPluginManager().registerEvents(new SeafrontBlockItemListener(), this);

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

    public static Seafront getInstance() {
        return instance;
    }

}
