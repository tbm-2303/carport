package business.persistence;

import business.entities.Order;
import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }
    public Order createOrder(Order order) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO order (user_id, price, profit, description, width, length, shed_length, shed_width, status) VALUES (?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, order.getUser_id());
                ps.setDouble(2, order.getPrice());
                ps.setDouble(3, order.getProfit());
                ps.setString(4,order.getInfo());
                ps.setInt(5, order.getWidth());
                ps.setInt(6, order.getLength());
                ps.setInt(7, order.getShed_length());
                ps.setInt(8, order.getShed_width());
                ps.setString(9,"confirmed");
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                order.setId(id);
                return order;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException | UserException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }
}
