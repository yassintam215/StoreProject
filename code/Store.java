
package code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable {
    private List<Product> products;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;
    private double foodMarkupPercent;
    private double nonFoodMarkupPercent;
    private int daysBeforeExpiry;
    private double discountPercent;

    public Store(double foodMarkupPercent, double nonFoodMarkupPercent, int daysBeforeExpiry, double discountPercent) {
        this.products = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.foodMarkupPercent = foodMarkupPercent;
        this.nonFoodMarkupPercent = nonFoodMarkupPercent;
        this.daysBeforeExpiry = daysBeforeExpiry;
        this.discountPercent = discountPercent;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public double getFoodMarkupPercent() {
        return foodMarkupPercent;
    }

    public double getNonFoodMarkupPercent() {
        return nonFoodMarkupPercent;
    }

    public int getDaysBeforeExpiry() {
        return daysBeforeExpiry;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double calculateTotalSalaries() {
        return cashiers.stream().mapToDouble(Cashier::getMonthlySalary).sum();
    }

    public double calculateTotalPurchaseCost() {
        return products.stream().mapToDouble(p -> p.getPurchasePrice() * p.getQuantity()).sum();
    }

    public double calculateTotalRevenue() {
        return receipts.stream().mapToDouble(Receipt::getTotalAmount).sum();
    }

    public double calculateProfit() {
        return calculateTotalRevenue() - (calculateTotalSalaries() + calculateTotalPurchaseCost());
    }
}
