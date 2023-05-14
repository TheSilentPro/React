package tsp.react.core.listener;

import com.jeff_media.customblockdata.CustomBlockData;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataContainer;
import tsp.react.React;
import tsp.react.core.util.PersistentUUIDDataType;
import tsp.react.core.util.Utils;
import tsp.react.implementation.ReactableItem;
import tsp.react.implementation.handler.Handler;
import tsp.react.implementation.handler.block.BlockBreakHandler;

import java.util.Optional;

public class BlockBreakListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Block block = event.getBlock();
        PersistentDataContainer container = new CustomBlockData(block, React.getInstance().getPlugin());
        Optional.ofNullable(container.get(Utils.ID, PersistentUUIDDataType.TYPE))
                .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                .ifPresent(reactable -> {
                    React.getInstance().getReactableManager().set(new ReactableItem(reactable));
                    container.remove(Utils.ID);
                    for (Handler entry : reactable.getHandlers()) {
                        if (entry instanceof BlockBreakHandler handler) {
                            handler.onBlockBreak(event);
                        }
                    }
                });
    }

}
