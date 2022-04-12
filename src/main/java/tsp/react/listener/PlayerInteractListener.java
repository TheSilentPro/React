package tsp.react.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import tsp.react.React;
import tsp.react.reactable.ReactBlock;
import tsp.react.reactable.ReactItem;
import tsp.react.reactable.Reactable;
import tsp.react.util.ReactBlockData;

import java.util.Optional;

public class PlayerInteractListener implements Listener {

    private final React react;

    public PlayerInteractListener(React react) {
        this.react = react;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // ReactItem
        ItemStack item = event.getItem();
        if (item == null || item.getItemMeta() == null) {
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

        if (reactable instanceof ReactItem) {
            ((ReactItem) reactable).onInteract(event);
        }

        // ReactBlock
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }

        ReactBlockData data = new ReactBlockData(block, react.getPlugin());
        if (data.isEmpty()) {
            return;
        }

        String blockId = data.get(react.getManager().getReactId(), PersistentDataType.STRING);
        if (blockId == null) {
            return;
        }

        Optional<Reactable> optionalBlock = react.getManager().get(NamespacedKey.fromString(blockId));
        if (!optionalBlock.isPresent()) {
            return;
        }
        Reactable reactableBlock = optional.get();

        if (reactableBlock instanceof ReactBlock) {
            ((ReactBlock) reactable).onInteract(event);
        }
    }

}
