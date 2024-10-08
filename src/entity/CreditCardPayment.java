package entity;

public class CreditCardPayment extends Payment {
    private String cardNumber;

    public CreditCardPayment(String payerName, double amount, String cardNumber) {
        super(payerName, amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment for " + payerName + " of amount $" + amount);
    }
}
