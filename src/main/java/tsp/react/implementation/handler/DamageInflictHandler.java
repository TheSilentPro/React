package tsp.react.implementation.handler;

import tsp.react.implementation.event.EntityHitEvent;

public interface DamageInflictHandler extends Handler {

    void onDamageInflict(EntityHitEvent event);

}
