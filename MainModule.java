package main;

import dao.ItemDAO;
import dao.OrderDAO;
import entity.*;
import exception.InvalidPriceException;
import exception.InsufficientPaymentException;
import util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static ShoppingCart cart = new ShoppingCart();
    private static ItemDAO itemDAO;  // Initialize your implementation class for ItemDAO
    private static OrderDAO orderDAO; // Initialize your implementation class for OrderDAO
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- ShopSmart Menu ---");
            System.out.println("1. Browse Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Remove Item from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Manage Inventory (For Store Managers)");
            System.out.println("7. View Order History");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    browseItems();
                    break;
                case 2:
                    addItemToCart();
                    break;
                case 3:
                    removeItemFromCart();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:2
                
                    checkout();
                    break;
                case 6:
                    manageInventory();
                    break;
                case 7:
                    viewOrderHistory();
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting ShopSmart. Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Browse available items
    private static void browseItems() {
        List<Item> items = itemDAO.getItems();
        if (items.isEmpty()) {
            System.out.println("No items available.");
        } else {
            items.forEach(System.out::println);
        }
    }

    // Add item to cart
    private static void addItemToCart() {
        System.out.print("Enter item name: ");
        String itemName = sc.next();
        List<Item> items = itemDAO.getItems();
        Item itemToAdd = items.stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (itemToAdd != null) {
            cart.addItem(itemToAdd);
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Remove item from cart
    private static void removeItemFromCart() {
        System.out.print("Enter item name to remove from cart: ");
        String itemName = sc.next();
        List<Item> cartItems = cart.getCartItems();
        Item itemToRemove = cartItems.stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (itemToRemove != null) {
            cart.removeItem(itemToRemove);
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    // View all items in the cart
    private static void viewCart() {
        cart.listCartItems();
    }

    // Checkout process
    private static void checkout() {
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Total price: " + cart.calculateTotalPrice());
        System.out.print("Enter payer name: ");
        String payerName = sc.next();
        System.out.println("Choose payment method: ");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        int paymentChoice = sc.nextInt();

        switch (paymentChoice) {
            case 1:
                System.out.print("Enter credit card number: ");
                String cardNumber = sc.next();
                Payment creditCardPayment = new CreditCardPayment(payerName, cart.calculateTotalPrice(), cardNumber);
                creditCardPayment.processPayment();
                break;
            case 2:
                System.out.print("Enter cash received: ");
                double cashReceived = sc.nextDouble();
                try {
                    Payment cashPayment = new CashPayment(payerName, cart.calculateTotalPrice(), cashReceived);
                    cashPayment.processPayment();
                } catch (InsufficientPaymentException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                break;
            default:
                System.out.println("Invalid payment option.");
                return;
        }

        // Place order
        String orderId = "ORD" + System.currentTimeMillis();
        Order order = new Order(orderId, new ArrayList<>(cart.getCartItems()));
        orderDAO.placeOrder(order);
        System.out.println("Order placed successfully!");

        cart.clearCart();
    }

    // Manage Inventory (for Store Managers)
    private static void manageInventory() {
        System.out.println("\n--- Inventory Management ---");
        System.out.println("1. Add Item to Inventory");
        System.out.println("2. Update Item in Inventory");
        System.out.println("3. Delete Item from Inventory");
        int managerChoice = sc.nextInt();

        switch (managerChoice) {
            case 1:
                addItemToInventory();
                break;
            case 2:
                updateItemInInventory();
                break;
            case 3:
                deleteItemFromInventory();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // Add item to inventory
    private static void addItemToInventory() {
        try {
            System.out.print("Enter item name: ");
            String itemName = sc.next();
            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            if (price <= 0) {
                throw new InvalidPriceException("Price must be a positive value.");
            }
            System.out.print("Enter category: ");
            String category = sc.next();
            Item item = new Item(itemName, price, category);
            itemDAO.addItem(item);
            System.out.println("Item added successfully.");
        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update item in inventory
    private static void updateItemInInventory() {
        System.out.print("Enter item name to update: ");
        String itemName = sc.next();
        List<Item> items = itemDAO.getItems();
        Item itemToUpdate = items.stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (itemToUpdate != null) {
            System.out.print("Enter new price: ");
            double newPrice = sc.nextDouble();
            System.out.print("Enter new category: ");
            String newCategory = sc.next();
            itemToUpdate.setPrice(newPrice);
            itemToUpdate.setCategory(newCategory);
            itemDAO.updateItem(itemToUpdate);
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Delete item from inventory
    private static void deleteItemFromInventory() {
        System.out.print("Enter item name to delete: ");
        String itemName = sc.next();
        itemDAO.deleteItem(itemName);
        System.out.println("Item deleted successfully.");
    }

    // View order history for a user
    private static void viewOrderHistory() {
        System.out.print("Enter user ID to view order history: ");
        String userID = sc.next();
        List<Order> orders = orderDAO.getOrderHistory(userID);
        if (orders.isEmpty()) {
            System.out.println("No order history found.");
        } else {
            orders.forEach(order -> {
                System.out.println("Order ID: " + order.getOrderID());
                order.getItemList().forEach(System.out::println);
                System.out.println("Total Price: " + order.getTotalPrice());
                System.out.println("------");
            });
        }
    }
}
