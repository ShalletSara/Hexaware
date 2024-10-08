package dao;

import entity.Order;
import entity.Item;
import util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    // Retrieve all orders from the database
    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String orderId = rs.getString("orderID");
                double totalPrice = rs.getDouble("totalPrice");
                
                // Fetch the items for this order (assuming you have a relationship)
                List<Item> itemList = getOrderItems(orderId);

                Order order = new Order(orderId, itemList, totalPrice);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Add a new order to the database
    @Override
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (orderID, totalPrice) VALUES (?, ?)";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, order.getOrderID());
            pstmt.setDouble(2, order.getTotalPrice());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order added successfully.");
            }

            // Insert the items associated with this order into the order_items table
            addOrderItems(order.getOrderID(), order.getItemList());

        } catch (SQLException e) {
            throw new SQLException("Error adding order to the database.", e);
        }
    }

    // Helper method to add items associated with an order
    private void addOrderItems(String orderId, List<Item> itemList) throws SQLException {
        String query = "INSERT INTO order_items (orderID, itemName) VALUES (?, ?)";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            for (Item item : itemList) {
                pstmt.setString(1, orderId);
                pstmt.setString(2, item.getItemName());
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new SQLException("Error adding items for order " + orderId, e);
        }
    }

    // Helper method to retrieve items for a specific order
    private List<Item> getOrderItems(String orderId) throws SQLException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT i.itemName, i.price, i.category FROM items i " +
                       "INNER JOIN order_items oi ON i.itemName = oi.itemName WHERE oi.orderID = ?";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("itemName");
                double price = rs.getDouble("price");
                String category = rs.getString("category");

                Item item = new Item(name, price, category);
                items.add(item);
            }

        } catch (SQLException e) {
            throw new SQLException("Error retrieving items for order " + orderId, e);
        }

        return items;
    }
}
