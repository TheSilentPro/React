package tsp.react.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import tsp.react.React;
import tsp.react.reactable.ReactBlock;
import tsp.react.reactable.Reactable;
import tsp.react.util.ReactBlockData;

import java.util.Optional;

public class BlockPlaceListener implements Listener {

    private final React react;

    public BlockPlaceListener(React react) {
        this.react = react;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (!item.hasItemMeta()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        String id = meta.getPersistentDataContainer().get(react.getManager().getReactId(), PersistentDataType.STRING);
        if (id == null) {
            return;
        }

        Optional<Reactable> optional = react.getManager().get(NamespacedKey.fromString(id));
        if (!optional.isPresent()) {
            return;
        }
        Reactable reactable = optional.get();

        if (reactable instanceof ReactBlock) {
            ((ReactBlock) reactable).onPlace(event);

            if (!event.isCancelled()) {
                Block block = event.getBlockPlaced();
                ReactBlockData data = new ReactBlockData(block, react.getPlugin());
                data.set(react.getManager().getReactId(), PersistentDataType.STRING, id);
            }
        }
    }

}
