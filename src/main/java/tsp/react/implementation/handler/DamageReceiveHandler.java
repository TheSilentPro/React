package tsp.react.implementation.handler;

import tsp.react.implementation.event.EntityHitEvent;

/**
 * Called when an armor piece detects damage taken.
 *
 * @author TheSilentPro (Silent)
 */
public interface DamageReceiveHandler extends Handler {

    void onDamageReceive(EntityHitEvent event);

}
