package net.starbasic.clients.data;

public abstract class Client {
    protected String name;
    protected double baseAmount;
    protected String type;

    public Client(String name, double baseAmount, String type) {
        this.name = name;
        this.baseAmount = baseAmount;
        this.type = type;
    }

    public String getName() { return name; }
    public double getBaseAmount() { return baseAmount; }
    public String getType() { return type; }

    public abstract double calculateTotal();

    @Override
    public String toString() {
        return String.format("[%s] %s | Сума послуг: %.2f | До сплати: %.2f",
                type, name, baseAmount, calculateTotal());
    }
}