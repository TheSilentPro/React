package tsp.react.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import tsp.react.React;
import tsp.react.core.util.PersistentDataAPI;
import tsp.react.core.util.Utils;
import tsp.react.implementation.handler.Handler;
import tsp.react.implementation.handler.ItemDeselectHandler;
import tsp.react.implementation.handler.ItemSelectHandler;

import java.util.Optional;

public class PlayerItemHeldListener extends AListener {

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        ItemStack next = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (next != null) {
            Optional.ofNullable(next.getItemMeta())
                    .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                    .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                    .ifPresent(reactable -> {
                        for (Handler handler : reactable.getHandlers()) {
                            if (handler instanceof ItemSelectHandler) {
                                ((ItemSelectHandler) handler).onItemSelect(event);
                            }
                        }
                    });
        }

        ItemStack item = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
        if (item != null) {
            Optional.ofNullable(item.getItemMeta())
                    .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                    .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                    .ifPresent(reactable -> {
                        for (Handler handler : reactable.getHandlers()) {
                            if (handler instanceof ItemDeselectHandler) {
                                ((ItemDeselectHandler) handler).onItemDeselect(event);
                            }
                        }
                    });
        }
    }

}
