package tsp.react;

import org.bukkit.plugin.java.JavaPlugin;
import tsp.react.core.listener.*;
import tsp.react.core.manager.ReactableManager;
import tsp.react.core.util.TickTask;

public class React {

    private static React instance;
    private final JavaPlugin plugin;
    private ReactableManager reactableManager;
    private TickTask tickTask;

    public React(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        instance = this;
        this.reactableManager = new ReactableManager();

        new EntityDamageByEntityListener();
        new BlockPlaceListener();
        new BlockBreakListener();
        new PlayerInteractListener();
        new PlayerItemHeldListener();

        tickTask = new TickTask(reactableManager);
        //tickTask.schedule(this);
    }

    public TickTask getTickTask() {
        return tickTask;
    }

    public ReactableManager getReactableManager() {
        return reactableManager;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public static React getInstance() {
        return instance;
    }

}