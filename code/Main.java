
package code;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Store store = new Store(20.0, 30.0, 5, 10.0);
        Cashier yasin = new Cashier(1, "Ясин Тамим", 1500.0);
        Cashier yoan = new Cashier(2, "Йоан Мишев", 1400.0);
        store.addCashier(yasin);
        store.addCashier(yoan);

        FoodProduct yogurt = new FoodProduct(101, "Кисело мляко", 1.0, LocalDate.now().plusDays(3), 50);
        FoodProduct eggs = new FoodProduct(102, "Яйца", 0.2, LocalDate.now().plusDays(7), 100);
        FoodProduct sugar = new FoodProduct(103, "Захар", 0.8, LocalDate.now().plusDays(30), 80);
        NonFoodProduct soap = new NonFoodProduct(201, "Сапун", 1.5, LocalDate.now().plusMonths(12), 30);
        NonFoodProduct toiletPaper = new NonFoodProduct(202, "Тоалетна хартия", 0.5, LocalDate.now().plusMonths(6), 60);
        NonFoodProduct cleaner = new NonFoodProduct(203, "Препарат", 2.5, LocalDate.now().plusMonths(8), 40);

        store.addProduct(yogurt);
        store.addProduct(eggs);
        store.addProduct(sugar);
        store.addProduct(soap);
        store.addProduct(toiletPaper);
        store.addProduct(cleaner);

        try {
            Receipt receipt1 = new Receipt(yasin);
            receipt1.addProduct(yogurt, 5, yogurt.getSellingPrice(store.getDaysBeforeExpiry(), store.getDiscountPercent(), store.getFoodMarkupPercent()));
            receipt1.addProduct(eggs, 12, eggs.getSellingPrice(store.getDaysBeforeExpiry(), store.getDiscountPercent(), store.getFoodMarkupPercent()));
            receipt1.addProduct(soap, 3, soap.getSellingPrice(store.getDaysBeforeExpiry(), store.getDiscountPercent(), store.getNonFoodMarkupPercent()));
            yogurt.reduceQuantity(5);
            eggs.reduceQuantity(12);
            soap.reduceQuantity(3);
            store.addReceipt(receipt1);
            receipt1.printReceipt();
            receipt1.saveToFile();
        } catch (InsufficientQuantityException e) {
            System.out.println(e.getMessage());
        }

        try {
            Receipt receipt2 = new Receipt(yoan);
            receipt2.addProduct(sugar, 10, sugar.getSellingPrice(store.getDaysBeforeExpiry(), store.getDiscountPercent(), store.getFoodMarkupPercent()));
            receipt2.addProduct(toiletPaper, 5, toiletPaper.getSellingPrice(store.getDaysBeforeExpiry(), store.getDiscountPercent(), store.getNonFoodMarkupPercent()));
            receipt2.addProduct(cleaner, 2, cleaner.getSellingPrice(store.getDaysBeforeExpiry(), store.getDiscountPercent(), store.getNonFoodMarkupPercent()));
            sugar.reduceQuantity(10);
            toiletPaper.reduceQuantity(5);
            cleaner.reduceQuantity(2);
            store.addReceipt(receipt2);
            receipt2.printReceipt();
            receipt2.saveToFile();
        } catch (InsufficientQuantityException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- Статистика за магазина ---");
        System.out.printf("Общ брой касови бележки: %d%n", store.getReceipts().size());
        System.out.printf("Общ приход: %.2f лв.%n", store.calculateTotalRevenue());
        System.out.printf("Общи разходи за заплати: %.2f лв.%n", store.calculateTotalSalaries());
        System.out.printf("Общи разходи за стоки: %.2f лв.%n", store.calculateTotalPurchaseCost());
        System.out.printf("Печалба: %.2f лв.%n", store.calculateProfit());

        SerializationUtils.serialize(store, "store.ser");
        Store loadedStore = (Store) SerializationUtils.deserialize("store.ser");
        System.out.println("\nМагазинът е зареден от файл. Общ брой касови бележки: " + loadedStore.getReceipts().size());
    }
}
