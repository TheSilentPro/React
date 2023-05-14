package tsp.react.core.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import tsp.react.React;
import tsp.react.core.util.PersistentDataAPI;
import tsp.react.core.util.Utils;
import tsp.react.implementation.Reactable;
import tsp.react.implementation.event.EntityHitEvent;
import tsp.react.implementation.handler.DamageInflictHandler;
import tsp.react.implementation.handler.DamageReceiveHandler;
import tsp.react.implementation.handler.Handler;

import java.util.Optional;

public class EntityDamageByEntityListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }

        // Damager
        if (event.getDamager() instanceof LivingEntity damager) {
            /*
            EntityEquipment equipment = damager.getEquipment();
            if (equipment != null) {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (damager instanceof Player && slot == EquipmentSlot.HAND) {
                        continue; // Ignore main hand slot of player, handled below.
                    }

                    ItemStack item = equipment.getItem(slot);
                    Optional.ofNullable(item.getItemMeta())
                            .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                            .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                            .ifPresent(reactable -> triggerDamagerHandlers(reactable, event));
                }
            }
            */

            if (damager instanceof Player player) {
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item == null) {
                        continue;
                    }

                    Optional.ofNullable(item.getItemMeta())
                            .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                            .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                            .ifPresent(reactable -> triggerDamagerHandlers(reactable, event, item));
                }
            }
        }

        // Victim
        if (event.getEntity() instanceof LivingEntity victim) {
            /*
            EntityEquipment equipment = victim.getEquipment();
            if (equipment != null) {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (victim instanceof Player && slot == EquipmentSlot.HAND) {
                        continue; // Ignore main hand slot of player, handled below.
                    }

                    ItemStack item = equipment.getItem(slot);
                    Optional.ofNullable(item.getItemMeta())
                            .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                            .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                            .ifPresent(reactable -> triggerVictimHandlers(reactable, event));
                }
            }
             */

            if (victim instanceof Player player) {
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item == null) {
                        continue;
                    }

                    Optional.ofNullable(item.getItemMeta())
                            .flatMap(meta -> PersistentDataAPI.getOptionalUUID(meta, Utils.ID))
                            .flatMap(id -> React.getInstance().getReactableManager().getReactable(id))
                            .ifPresent(reactable -> triggerVictimHandlers(reactable, event, item));
                }
            }
        }
    }

    private void triggerDamagerHandlers(Reactable reactable, EntityDamageByEntityEvent event, ItemStack item) {
        for (Handler handler : reactable.getHandlers()) {
            if (handler instanceof DamageInflictHandler damageInflictHandler) {
                damageInflictHandler.onDamageInflict(new EntityHitEvent(event, item));
            }
        }
    }

    private void triggerVictimHandlers(Reactable reactable, EntityDamageByEntityEvent event, ItemStack item) {
        for (Handler handler : reactable.getHandlers()) {
            if (handler instanceof DamageReceiveHandler damageReceiveHandler) {
                damageReceiveHandler.onDamageReceive(new EntityHitEvent(event, item));
            }
        }
    }

}
