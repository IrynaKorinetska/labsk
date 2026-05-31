package net.starbasic.clients.data;

public class PartnerCompany extends Client {
    private double partnerDiscount;

    public PartnerCompany(String name, double baseAmount, double partnerDiscount) {
        super(name, baseAmount, "Партнер");
        this.partnerDiscount = partnerDiscount;
    }

    @Override
    public double calculateTotal() {
        return baseAmount - partnerDiscount;
    }
}