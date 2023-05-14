package tsp.react.implementation.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class EntityHitEvent extends EntityDamageByEntityEvent {

    private final ItemStack item;

    public EntityHitEvent(Entity damager, Entity damagee, ItemStack item, DamageCause cause, double damage) {
        super(damager, damagee, cause, damage);
        this.item = item;
    }

    public EntityHitEvent(EntityDamageByEntityEvent event, ItemStack item) {
        this(event.getDamager(), event.getEntity(), item, event.getCause(), event.getDamage());
    }

    /**
     * The item that is triggering this event.
     *
     * @return The trigger item.
     */
    public Optional<ItemStack> getItem() {
        return Optional.ofNullable(item);
    }

    public Entity getVictim() {
        return getEntity();
    }

    /**
     * Checks if the cause of the trigger is a Sweep Attack.
     * Useful if the trigger is from a sweep attack (i.e. Not the main target!).
     *
     * @return If the cause is from a sweep attack.
     */
    public boolean isSweepAttack() {
        return getCause() == DamageCause.ENTITY_SWEEP_ATTACK;
    }

    public Optional<LivingEntity> asLivingDamager() {
        if (getDamager() instanceof LivingEntity le) {
            return Optional.of(le);
        } else {
            return Optional.empty();
        }
    }

    public Optional<LivingEntity> asLivingVictim() {
        if (getEntity() instanceof LivingEntity le) {
            return Optional.of(le);
        } else {
            return Optional.empty();
        }
    }

}
