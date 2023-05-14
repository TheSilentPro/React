package tsp.react.implementation;

import tsp.react.core.util.SerializedLocation;
import tsp.react.implementation.handler.Handler;

import java.util.List;
import java.util.UUID;

public class ReactableBlock extends Reactable {

    public ReactableBlock(UUID uniqueId, UUID parentUniqueId, List<Handler> handlers) {
        super(uniqueId, parentUniqueId, handlers);
    }

    public ReactableBlock(Reactable reactable) {
        this(reactable.getUniqueId(), reactable.getParentUniqueId(), reactable.getHandlers());
    }

    public void tick(SerializedLocation location) {}

}
