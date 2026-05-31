package net.starbasic.clients.data;

public class RetailClient extends Client {
    public RetailClient(String name, double baseAmount) {
        super(name, baseAmount, "Роздрібний");
    }

    @Override
    public double calculateTotal() {
        return baseAmount * 1.05; // 5% націнка
    }
}