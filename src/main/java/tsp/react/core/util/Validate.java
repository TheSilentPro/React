package tsp.react.core.util;

public class Validate {

    public static <T> void notNull(T t, String message) {
        if (t == null) {
            throw new NullPointerException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

}
