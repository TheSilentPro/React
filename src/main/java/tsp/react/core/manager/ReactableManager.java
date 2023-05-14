package tsp.react.core.manager;

import tsp.react.core.util.Validate;
import tsp.react.implementation.Reactable;

import javax.annotation.Nonnull;
import java.util.*;

public class ReactableManager {

    private final Map<UUID, Reactable> reactables = new HashMap<>();

    public void set(@Nonnull Reactable reactable) {
        Validate.notNull(reactable, "Reactable can not be null!");

        this.reactables.put(reactable.getUniqueId(), reactable);
    }

    @Nonnull
    public Optional<Reactable> getReactable(UUID uuid) {
        return Optional.ofNullable(this.reactables.get(uuid));
    }

    @Nonnull
    public Map<UUID, Reactable> getReactables() {
        return Collections.unmodifiableMap(reactables);
    }

}