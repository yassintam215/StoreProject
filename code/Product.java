
package code;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Product implements Serializable {
    private int id;
    private String name;
    private double purchasePrice;
    private LocalDate expiryDate;
    private int quantity;

    public Product(int id, String name, double purchasePrice, LocalDate expiryDate, int quantity) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) throws InsufficientQuantityException {
        if (amount > quantity) {
            throw new InsufficientQuantityException(name, amount - quantity);
        }
        quantity -= amount;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public abstract double getSellingPrice(int daysBeforeExpiry, double discountPercent, double markupPercent);

    @Override
    public String toString() {
        return name + " (ID: " + id + ", Quantity: " + quantity + ")";
    }
}
