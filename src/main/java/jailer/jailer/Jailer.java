package jailer.jailer;

import org.bukkit.plugin.java.JavaPlugin;

public final class Jailer extends JavaPlugin {

    private static Jailer instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Jailer getInstance() {
        return instance;
    }

}
