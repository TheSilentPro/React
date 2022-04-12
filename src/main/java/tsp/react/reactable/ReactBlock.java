package tsp.react.reactable;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public interface ReactBlock extends ReactItem {

    void onPlace(BlockPlaceEvent event);

    void onBreak(BlockBreakEvent event);

}
