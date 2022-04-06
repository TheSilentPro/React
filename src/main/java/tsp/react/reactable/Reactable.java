package tsp.react.reactable;

import org.bukkit.NamespacedKey;
import tsp.react.React;

public class Reactable {

    private final NamespacedKey key;

    public Reactable(NamespacedKey key) {
        this.key = key;
    }

    public void register(React react) {
        react.getManager().register(this);
    }

    public NamespacedKey getKey() {
        return key;
    }

}
