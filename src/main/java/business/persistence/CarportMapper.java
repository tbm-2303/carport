package business.persistence;

import business.entities.Carport;
import business.entities.Item;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarportMapper {
    private List<Item> itemList;
    private Database database;

    public CarportMapper(Database database) {
        this.database = database;
    }


    public List<Carport> getAllCarports() throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet rs = ps.executeQuery();
                List<Carport> carportList = new ArrayList<>();
                while (rs.next()) {
                    String info = rs.getString("info");
                    double price = rs.getDouble("price");
                    double profit = rs.getDouble("profit");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int shed_width = rs.getInt("shed_width");
                    int shed_length = rs.getInt("shed_length");
                    ResultSet ids = ps.getGeneratedKeys();
                    ids.next();
                    int id = ids.getInt(1);
                    Carport carport = new Carport(price, length, width, shed_length, shed_width, "flat", info);
                    carport.setId(id);
                    carportList.add(carport);
                }
                return carportList;
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Carport CreateCarportCustom(Carport carport) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO carport (price, selling_price, info, length, width, shed_length, shed_width, custom) VALUES (?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, carport.getPrice());
                ps.setDouble(2, carport.getProfit());
                ps.setString(3, "info");
                ps.setInt(4, carport.getLength());
                ps.setInt(5, carport.getWidth());
                ps.setInt(6, carport.getShed_length());
                ps.setInt(7, carport.getShed_width());
                ps.setInt(8, 1);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                carport.setId(id);
                return carport;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }




    public Carport getCarport(int carport_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport WHERE carport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String info = rs.getString("info");
                    double price = rs.getDouble("price");
                    double profit = rs.getDouble("profit");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int shed_width = rs.getInt("shed_width");
                    int shed_length = rs.getInt("shed_length");
                    Carport carport = new Carport(price, length, width, shed_length, shed_width, "flat", info);
                    carport.setId(carport_id);
                    return carport;
                }
            }
        }
        catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }

        return null;
    }
}
