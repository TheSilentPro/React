package tsp.react.implementation.handler.block;

import org.bukkit.event.block.BlockBreakEvent;
import tsp.react.implementation.handler.Handler;

/**
 * @author TheSilentPro (Silent)
 */
public interface BlockBreakHandler extends Handler {

    void onBlockBreak(BlockBreakEvent event);

}
