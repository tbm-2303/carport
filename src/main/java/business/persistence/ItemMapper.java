package business.persistence;

import business.entities.Carport;
import business.entities.Item;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    private List<Item> itemList;
    private Database database;

    public ItemMapper(Database database) {
        this.database = database;
    }



    public Item SelectItemFromDB(String name, int length) throws SQLException, UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM item WHERE `name` = ? and length = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, length);
                ResultSet rs = ps.executeQuery();
                List<Item> itemList2 = new ArrayList<>();
                if (rs.next()) {
                    int item_id = rs.getInt("item_id");
                    double price = rs.getDouble("price");
                    int width = rs.getInt("width");
                    Item item = new Item(item_id,name,price,length,width);
                    return item;
                }
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return null;
    }


}
