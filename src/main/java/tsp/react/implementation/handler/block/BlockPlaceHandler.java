package tsp.react.implementation.handler.block;

import org.bukkit.event.block.BlockPlaceEvent;
import tsp.react.implementation.handler.Handler;

/**
 * @author TheSilentPro (Silent)
 */
public interface BlockPlaceHandler extends Handler {

    void onBlockPlace(BlockPlaceEvent event);

}
