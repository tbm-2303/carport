package business.persistence;

import business.entities.Request_obj;
import business.entities.Request;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper {
    private Database database;

    public RequestMapper(Database database) {
        this.database = database;
    }


    public List<Request> getAllRequest(String status) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request WHERE status_info = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, status);
                ResultSet rs = ps.executeQuery();
                List<Request> requestList = new ArrayList<>();
                while (rs.next()) {
                    int carport_id = rs.getInt("carport_id");
                    int user_id = rs.getInt("user_id");
                    String status_info = rs.getString("status_info");
                    ResultSet ids = ps.getGeneratedKeys();
                    ids.next();
                    int id = ids.getInt(1);
                    Request requesty = new Request(carport_id, user_id, status_info);
                    requesty.setRequest_id(id);
                    requestList.add(requesty);
                }
                return requestList;
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Request_obj CreateRequest(Request_obj request) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO request (carport_id, user_id, status_info) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, request.getCarport().getId());
                ps.setDouble(2, request.getUser().getId());
                ps.setString(3, "requested");
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                request.setRequest_id(id);
                return request;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}

