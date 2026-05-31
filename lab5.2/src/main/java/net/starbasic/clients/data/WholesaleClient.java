package net.starbasic.clients.data;

public class WholesaleClient extends Client {
    public WholesaleClient(String name, double baseAmount) {
        super(name, baseAmount, "Оптовий");
    }

    @Override
    public double calculateTotal() {
        return baseAmount * 0.85; // 15% знижка
    }
}