
package code;

import java.io.Serializable;

public class CashRegister implements Serializable {
    private int id;
    private Cashier cashier;

    public CashRegister(int id, Cashier cashier) {
        this.id = id;
        this.cashier = cashier;
    }

    public int getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    @Override
    public String toString() {
        return "CashRegister " + id + " operated by " + cashier.getName();
    }
}
