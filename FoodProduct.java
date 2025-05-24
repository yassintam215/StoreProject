
package code;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FoodProduct extends Product {

    public FoodProduct(int id, String name, double purchasePrice, LocalDate expiryDate, int quantity) {
        super(id, name, purchasePrice, expiryDate, quantity);
    }

    @Override
    public double getSellingPrice(int daysBeforeExpiry, double discountPercent, double markupPercent) {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getExpiryDate());
        double price = getPurchasePrice() * (1 + markupPercent / 100);
        if (daysLeft >= 0 && daysLeft <= daysBeforeExpiry) {
            price *= (1 - discountPercent / 100);
        }
        return price;
    }
}
