package tsp.react.core.listener;

import com.jeff_media.customblockdata.CustomBlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import tsp.react.React;
import tsp.react.core.util.PersistentDataAPI;
import tsp.react.core.util.PersistentUUIDDataType;
import tsp.react.core.util.SerializedLocation;
import tsp.react.core.util.Utils;
import tsp.react.implementation.ReactableBlock;
import tsp.react.implementation.handler.Handler;
import tsp.react.implementation.handler.block.BlockPlaceHandler;

import java.util.Optional;

public class BlockPlaceListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }

        ItemStack item = event.getItemInHand();
        Optional.ofNullable(item.getItemMeta())
                .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                .ifPresent(reactable -> {
                    // Set to ReactableBlock in the registry and update location for ticking.
                    React.getInstance().getReactableManager().set(new ReactableBlock(reactable));
                    React.getInstance().getTickTask().set(reactable.getUniqueId(), SerializedLocation.of(event.getBlock().getLocation()));
                    // Save the ID of the reactable inside the block(chunk) pdc.
                    new CustomBlockData(event.getBlockPlaced(), React.getInstance().getPlugin()).set(Utils.ID, PersistentUUIDDataType.TYPE, reactable.getUniqueId());
                    // Search and trigger place handlers
                    for (Handler entry : reactable.getHandlers()) {
                        if (entry instanceof BlockPlaceHandler handler) {
                            handler.onBlockPlace(event);
                        }
                    }
                });
    }

}
