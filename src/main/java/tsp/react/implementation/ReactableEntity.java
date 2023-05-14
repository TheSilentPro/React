package tsp.react.implementation;

import org.bukkit.entity.Entity;
import tsp.react.implementation.handler.Handler;

import java.util.List;
import java.util.UUID;

public class ReactableEntity extends Reactable {

    public ReactableEntity(UUID uniqueId, UUID parentUniqueId, List<Handler> handlers) {
        super(uniqueId, parentUniqueId, handlers);
    }

    public ReactableEntity(Reactable reactable) {
        this(reactable.getUniqueId(), reactable.getParentUniqueId(), reactable.getHandlers());
    }

    public void tick(Entity entity) {}

}
