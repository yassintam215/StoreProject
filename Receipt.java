
package code;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Receipt implements Serializable {
    private static int counter = 0;
    private int number;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private Map<Product, Integer> products;
    private double totalAmount;

    public Receipt(Cashier cashier) {
        this.number = ++counter;
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.products = new HashMap<>();
        this.totalAmount = 0.0;
    }

    public void addProduct(Product product, int quantity, double price) {
        products.put(product, quantity);
        totalAmount += price * quantity;
    }

    public int getNumber() {
        return number;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void printReceipt() {
        System.out.println("Receipt #" + number);
        System.out.println("Cashier: " + cashier.getName());
        System.out.println("Date: " + dateTime);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            System.out.println(entry.getKey().getName() + " x" + entry.getValue());
        }
        System.out.println("Total: " + totalAmount);
    }

    public void saveToFile() {
        String filename = "receipt_" + number + ".txt";
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))) {
            writer.println("Receipt #" + number);
            writer.println("Cashier: " + cashier.getName());
            writer.println("Date: " + dateTime);
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                writer.println(entry.getKey().getName() + " x" + entry.getValue());
            }
            writer.println("Total: " + totalAmount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
