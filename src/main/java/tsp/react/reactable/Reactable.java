package tsp.react.reactable;

import org.bukkit.NamespacedKey;
import tsp.react.React;

public interface Reactable {

    NamespacedKey getKey();

    default void register(React react) {
        react.getManager().register(this);
    }

}
