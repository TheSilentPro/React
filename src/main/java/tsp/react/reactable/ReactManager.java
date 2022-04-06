package tsp.react.reactable;

import org.bukkit.NamespacedKey;
import tsp.react.React;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReactManager {

    public final NamespacedKey reactId;
    private final Map<NamespacedKey, Reactable> registry = new HashMap<>();

    public ReactManager(React react) {
        reactId = new NamespacedKey(react.getPlugin(), react.getPlugin().getName() + "_react_id");
    }

    public void register(Reactable reactable) {
        this.registry.put(reactable.getKey(), reactable);
    }

    public Optional<Reactable> get(NamespacedKey key) {
        return Optional.ofNullable(registry.get(key));
    }

    public Map<NamespacedKey, Reactable> getRegistry() {
        return Collections.unmodifiableMap(registry);
    }

    public NamespacedKey getReactId() {
        return reactId;
    }

}
