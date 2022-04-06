package tsp.react.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import tsp.react.React;
import tsp.react.reactable.Reactable;

public class ReactUtils {

    public static void giveItem(React react, Player player, ItemStack item, Reactable reactable) {
        ItemMeta meta = item.getItemMeta();
        mark(react, meta, reactable);
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }

    public static void mark(React react, PersistentDataHolder holder, Reactable reactable) {
        holder.getPersistentDataContainer().set(react.getManager().getReactId(), PersistentDataType.STRING, reactable.getKey().toString());
    }

}
