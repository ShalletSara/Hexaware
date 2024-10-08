package entity;

public abstract class Payment {
    protected String payerName;
    protected double amount;

    public Payment(String payerName, double amount) {
        this.payerName = payerName;
        this.amount = amount;
    }

    public abstract void processPayment();
}
