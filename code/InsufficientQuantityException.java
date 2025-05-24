
package code;

public class InsufficientQuantityException extends Exception {
    public InsufficientQuantityException(String productName, int shortage) {
        super("Недостатъчно количество от продукта: " + productName + ". Липсват " + shortage + " броя.");
    }
}
