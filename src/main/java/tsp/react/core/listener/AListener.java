package tsp.react.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tsp.react.React;

public class AListener implements Listener {

    public AListener() {
        Bukkit.getPluginManager().registerEvents(this, React.getInstance().getPlugin());
    }

}