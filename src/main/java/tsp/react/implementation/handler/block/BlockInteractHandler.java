package tsp.react.implementation.handler.block;

import org.bukkit.event.player.PlayerInteractEvent;
import tsp.react.implementation.handler.Handler;

/**
 * Called when a {@link org.bukkit.entity.Player} interacts with a {@link tsp.react.implementation.Reactable} block.
 * This is called twice per right-click, once for each hand.
 * Breaking the block also calls this handler.
 *
 * @author TheSilentPro (Silent)
 */
public interface BlockInteractHandler extends Handler {

    void onBlockInteract(PlayerInteractEvent event);

}
