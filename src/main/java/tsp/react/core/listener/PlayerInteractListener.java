package tsp.react.core.listener;

import com.jeff_media.customblockdata.CustomBlockData;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import tsp.react.React;
import tsp.react.core.util.PersistentUUIDDataType;
import tsp.react.core.util.Utils;
import tsp.react.implementation.handler.Handler;
import tsp.react.implementation.handler.block.BlockInteractHandler;

import java.util.Optional;

public class PlayerInteractListener extends AListener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.useInteractedBlock() == Event.Result.DENY) {
            return;
        }

        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }

        PersistentDataContainer container = new CustomBlockData(block, React.getInstance().getPlugin());
        Optional.ofNullable(container.get(Utils.ID, PersistentUUIDDataType.TYPE))
                .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                .ifPresent(reactable -> {
                    for (Handler entry : reactable.getHandlers()) {
                        if (entry instanceof BlockInteractHandler handler) {
                            handler.onBlockInteract(event);
                        }
                    }
                });
    }

}
