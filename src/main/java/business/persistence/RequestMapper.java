package business.persistence;

import business.entities.Carport;
import business.entities.Requesty;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper
{
    private Database database;

    public RequestMapper(Database database)
    {
        this.database = database;
    }


    public int createRequest(Requesty requesty) {
        int request_id = 0;
        return  request_id;
    }

    public List<Requesty> getAllRequest() throws UserException {
            try (Connection connection = database.connect()) {
                String sql = "SELECT * FROM request";

                try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ResultSet rs = ps.executeQuery();
                    List<Requesty> carportList = new ArrayList<>();
                    while (rs.next()) {
                        String info = rs.getString("info");
                        double price = rs.getDouble("price");
                        double profit = rs.getDouble("profit");
                        int length = rs.getInt("length");
                        int height = rs.getInt("height");
                        int shed_height = rs.getInt("shed_height");
                        int shed_length = rs.getInt("shed_length");
                        ResultSet ids = ps.getGeneratedKeys();
                        ids.next();
                        int id = ids.getInt(1);
                        //Carport carport = new Carport(price, length, height, shed_length, shed_height, "flat", info);
                        //carport.setId(id);
                       // carportList.add(carport);
                    }
                    return carportList;
                }
            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        }
    }

