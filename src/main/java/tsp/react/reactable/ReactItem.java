package tsp.react.reactable;

import org.bukkit.event.player.PlayerInteractEvent;

public interface ReactItem extends Reactable {

    void onInteract(PlayerInteractEvent event);

}
