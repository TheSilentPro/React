package tsp.react.implementation;

import org.bukkit.Bukkit;
import tsp.react.implementation.handler.Handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reactable implements Serializable {

    private final UUID uniqueId;
    private final UUID parentUniqueId;
    private final List<Handler> handlers;

    public Reactable(UUID uniqueId, UUID parentUniqueId, List<Handler> handlers) {
        this.uniqueId = uniqueId;
        this.parentUniqueId = parentUniqueId;
        this.handlers = handlers != null ? handlers : new ArrayList<>();
    }

    public Reactable(Reactable reactable) {
        this(reactable.getUniqueId(), reactable.getParentUniqueId(), reactable.getHandlers());
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public UUID getParentUniqueId() {
        return parentUniqueId;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public <T> List<T> getHandlers(Class<? extends T> clazz) {
        List<T> result = new ArrayList<>();
        Bukkit.broadcastMessage("handlers: " + handlers.size());
        
        for (Handler handler : handlers) {
            Bukkit.broadcastMessage("handler type: " + handler.getClass());
            if (handler.getClass().isInstance(clazz)) {
                //noinspection unchecked
                result.add((T) handler);

                /* Shouldn't be needed, as isInstance is a safeguard against this.
                try {
                    result.add((T) handler);
                } catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
                 */
            }
        }
        
        return result;
    }

}