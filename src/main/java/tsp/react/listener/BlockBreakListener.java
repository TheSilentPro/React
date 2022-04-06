package tsp.react.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataType;
import tsp.react.React;
import tsp.react.reactable.ReactBlock;
import tsp.react.reactable.Reactable;
import tsp.react.util.ReactBlockData;

import java.util.Optional;

public class BlockBreakListener implements Listener {

    private final React react;

    public BlockBreakListener(React react) {
        this.react = react;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        ReactBlockData data = new ReactBlockData(block, react.getPlugin());
        if (data.isEmpty()) {
            return;
        }

        String id = data.get(react.getManager().getReactId(), PersistentDataType.STRING);
        if (id == null) {
            return;
        }

        Optional<Reactable> optional = react.getManager().get(NamespacedKey.fromString(id));
        if (!optional.isPresent()) {
            return;
        }
        Reactable reactable = optional.get();

        if (reactable instanceof ReactBlock) {
            ((ReactBlock) reactable).onBreak(event);

            if (!event.isCancelled()) {
                data.remove(react.getManager().getReactId());
            }
        }
    }

}
