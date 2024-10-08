package dao;

import entity.Item;
import util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("itemName");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                Item item = new Item(name, price, category);
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void addItem(Item item) {
        String query = "INSERT INTO items (itemName, price, category) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, item.getItemName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setString(3, item.getCategory());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item added successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(String itemName) {
		// TODO Auto-generated method stub
		
	}

    // Similar methods for updateItem and deleteItem
}
