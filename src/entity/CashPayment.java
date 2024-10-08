package entity;

public class CashPayment extends Payment {
    private double cashReceived;

    public CashPayment(String payerName, double amount, double cashReceived) {
        super(payerName, amount);
        this.cashReceived = cashReceived;
    }

    @Override
    public void processPayment() {
        if (cashReceived >= amount) {
            System.out.println("Processing cash payment for " + payerName + ". Change: $" + (cashReceived - amount));
        } else {
            throw new InsufficientPaymentException("Insufficient funds provided for the payment.");
        }
    }
}
