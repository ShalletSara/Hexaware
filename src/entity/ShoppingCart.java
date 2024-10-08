package entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(Item item) {
        cartItems.add(item);
    }

    public void removeItem(Item item) {
        cartItems.remove(item);
    }

    public void listCartItems() {
        cartItems.forEach(System.out::println);
    }

    public void clearCart() {
        cartItems.clear();
    }
}
