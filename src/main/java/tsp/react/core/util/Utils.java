package tsp.react.core.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tsp.react.React;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

public class Utils {

    public static final NamespacedKey ID = new NamespacedKey(React.getInstance().getPlugin(), "id");

    /**
     * Inject the ID of the reactable into an ItemStack.
     *
     * @param item The item.
     * @param id The Unique ID.
     * @return Item with injected ID.
     */
    @Nonnull
    public static ItemStack injectId(@Nonnull ItemStack item, UUID id) {
        Validate.notNull(item, "Item can not be null!");
        Validate.notNull(item.getItemMeta(), "(Item)Meta can not be null!");

        ItemMeta meta = item.getItemMeta();
        PersistentDataAPI.setUUID(meta, Utils.ID, id);
        item.setItemMeta(meta);
        return item;
    }

    public static Optional<UUID> asUniqueId(String raw) {
        if (raw == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(UUID.fromString(raw));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

}
