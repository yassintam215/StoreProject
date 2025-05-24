
package code;

import java.time.LocalDate;

public class NonFoodProduct extends Product {

    public NonFoodProduct(int id, String name, double purchasePrice, LocalDate expiryDate, int quantity) {
        super(id, name, purchasePrice, expiryDate, quantity);
    }

    @Override
    public double getSellingPrice(int daysBeforeExpiry, double discountPercent, double markupPercent) {
        return getPurchasePrice() * (1 + markupPercent / 100);
    }
}
