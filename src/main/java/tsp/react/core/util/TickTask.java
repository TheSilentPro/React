package tsp.react.core.util;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tsp.react.React;
import tsp.react.core.manager.ReactableManager;
import tsp.react.implementation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TickTask implements Runnable {

    private final ReactableManager manager;

    private final Map<UUID, SerializedLocation> locations = new HashMap<>();

    public TickTask(ReactableManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        for (Reactable reactable : manager.getReactables().values()) {
            if (!(reactable instanceof Tickable)) {
                continue;
            }

            if (reactable instanceof ReactableItem reactableItem) {
                /*
                Player player = Bukkit.getPlayer(reactable.getParentUniqueId());
                if (player != null) {
                    for (ItemStack item : player.getInventory().getContents()) {
                        ItemMeta meta = item.getItemMeta();
                        if (meta != null) {
                            UUID id = PersistentDataAPI.getUUID(meta, Utils.ID);
                            if (id != null) {
                                Reactable r = React.getInstance().getReactableManager().getReactable(id).orElse(null);
                                if (r != null && r.getUniqueId().equals(reactable.getUniqueId())) {
                                    reactableItem.tick(item);
                                    break;
                                }
                            }
                        }
                    }
                }
                */
            } else if (reactable instanceof ReactableBlock reactableBlock) {
                // Get chunk from parent id
                World world = Bukkit.getWorld(reactable.getParentUniqueId());
                if (world != null) {
                    SerializedLocation location = locations.get(reactableBlock.getUniqueId());
                    if (location != null) {
                        reactableBlock.tick(location);
                    }
                }
            } else if (reactable instanceof ReactableEntity reactableEntity) {
                Entity entity = Bukkit.getEntity(reactableEntity.getUniqueId());
                if (entity != null) {
                    reactableEntity.tick(entity);
                }
            }
        }

        // Loop players to check for custom items
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        UUID id = PersistentDataAPI.getUUID(meta, Utils.ID);
                        if (id != null) {
                            Reactable reactable = React.getInstance().getReactableManager().getReactable(id).orElse(null);
                            if (reactable instanceof ReactableItem reactableItem) {
                                reactableItem.tick(item);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<UUID, SerializedLocation> getLocations() {
        return Collections.unmodifiableMap(locations);
    }

    public void set(UUID uuid, SerializedLocation serializedLocation) {
        this.locations.put(uuid, serializedLocation);
    }

}
