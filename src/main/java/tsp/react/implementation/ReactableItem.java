package tsp.react.implementation;

import org.bukkit.inventory.ItemStack;
import tsp.react.core.util.Utils;
import tsp.react.implementation.handler.Handler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a {@link  Reactable} item which can hold {@link Handler Handlers}.
 * Note: The default item is just for convenience, the item that holds the handlers might be different.
 *
 * @author TheSilentPro (Silent)
 */
public class ReactableItem extends Reactable {

    @Nullable
    private final ItemStack defaultItem;

    public ReactableItem(UUID uniqueId, List<Handler> handlers, @Nullable ItemStack defaultItem) {
        super(uniqueId, null, handlers);
        this.defaultItem = defaultItem != null ? Utils.injectId(defaultItem, uniqueId) : null;
    }

    public ReactableItem(Reactable reactable) {
        this(reactable.getUniqueId(), reactable.getHandlers(), null);
    }

    public void tick(ItemStack item) {}

    @Nonnull
    public Optional<ItemStack> getDefaultItem() {
        return Optional.ofNullable(defaultItem);
    }

}
