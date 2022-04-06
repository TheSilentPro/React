package tsp.react;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tsp.react.listener.BlockBreakListener;
import tsp.react.listener.BlockPlaceListener;
import tsp.react.listener.PlayerInteractListener;
import tsp.react.reactable.ReactManager;
import tsp.react.reactable.Reactable;
import tsp.react.util.ReactUtils;

public class React {

    private final JavaPlugin plugin;
    private final ReactManager manager;

    public React(JavaPlugin plugin) {
        this.plugin = plugin;
        this.manager = new ReactManager(this);
        PluginManager pluginManager = this.plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerInteractListener(this), plugin);
        pluginManager.registerEvents(new BlockPlaceListener(this), plugin);
        pluginManager.registerEvents(new BlockBreakListener(this), plugin);
    }

    public void mark(PersistentDataHolder holder, Reactable reactable) {
        ReactUtils.mark(this, holder, reactable);
    }

    public void giveItem(Player player, ItemStack item, Reactable reactable) {
        ReactUtils.giveItem(this, player, item, reactable);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public ReactManager getManager() {
        return manager;
    }

}
