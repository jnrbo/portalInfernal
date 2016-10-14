package model;

/**
 * Created by junior on 04/10/16.
 */
public class Assert {

    private static void notNull(Object object) {
        if (object == null) {
            throw new RuntimeException("Erro!");
        }
    }
}
