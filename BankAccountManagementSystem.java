package com.hexaware.Exceptions;

import java.util.Scanner;

public class BankAccountManagementSystem {

    private String name;
    private double balance;

    // Constructor
    public BankAccountManagementSystem(String name, double initialBalance) {
        this.setName(name);
        this.balance = initialBalance;
    }

    // Deposit money
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposit successful! Current balance: " + balance);
    }

    // Withdraw money
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. You only have " + balance + " available.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Current balance: " + balance);
    }

    // Check balance
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    // Custom Exceptions
    static class InvalidAmountException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidAmountException(String message) {
            super(message);
        }
    }

    static class InsufficientFundsException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InsufficientFundsException(String message) {
            super(message);
        }
    }

    static class InvalidChoiceException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidChoiceException(String message) {
            super(message);
        }
    }

    static class NoAccountException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoAccountException(String message) {
            super(message);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccountManagementSystem account = null;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();  // Consume the leftover newline character

                switch (choice) {
                    case 1:
                        if (account == null) {
                            System.out.print("Enter account holder name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter initial balance: ");
                            double balance = sc.nextDouble();
                            account = new BankAccountManagementSystem(name, balance);
                            System.out.println("Account created successfully for " + name + " with balance: " + balance);
                        } else {
                            System.out.println("Account already exists.");
                        }
                        break;
                    case 2:
                        checkIfAccountExists(account);
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        checkIfAccountExists(account);
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = sc.nextDouble();
                        account.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        checkIfAccountExists(account);
                        account.checkBalance();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Thank you for using the Bank Account Management System!");
                        break;
                    default:
                        throw new InvalidChoiceException("Invalid choice. Please select a valid option.");
                }
            } catch (InvalidAmountException | InsufficientFundsException | InvalidChoiceException | NoAccountException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        sc.close();
    }

    // Helper method to check if account exists
    private static void checkIfAccountExists(BankAccountManagementSystem account) throws NoAccountException {
        if (account == null) {
            throw new NoAccountException("Please create an account first.");
        }
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
