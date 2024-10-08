package dao;

import entity.Item;
import java.util.List;

public interface ItemDAO {
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(String itemName);
    List<Item> getItems();
}
