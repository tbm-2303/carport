package business.persistence;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.ItemFacade;
import business.services.UserFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper {
    private Database database;
    private CarportFacade carportFacade;
    private UserFacade userFacade;
    private ItemFacade itemFacade;

    public RequestMapper(Database database) {
        this.database = database;
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);
        itemFacade = new ItemFacade(database);

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

    public List<Request_obj> getAllRequest3(int user_id) throws UserException, SQLException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request WHERE user_id= ?;";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                List<Request_obj> requestList = new ArrayList<>();
                while (rs.next()) {
                    int request_id = rs.getInt("request_id");
                    int carport_id = rs.getInt("carport_id");
                    String status_info = rs.getString("status_info");
                    User user = userFacade.getUser(user_id);
                    Carport carport = carportFacade.getCarport(carport_id);
                    Request_obj request_obj = new Request_obj(user,carport,status_info);
                    request_obj.setRequest_id(request_id);
                    List<Item> itemlist = itemFacade.getItemList(carport_id);
                    request_obj.getCarport().setItemList(itemlist);
                    requestList.add(request_obj);
                }
                return requestList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Request_obj> getAllRequest2(String status) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request WHERE status_info = ?";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ResultSet rs = ps.executeQuery();
                List<Request_obj> requestList = new ArrayList<>();
                while (rs.next()) {
                    int request_id = rs.getInt("request_id");
                    int carport_id = rs.getInt("carport_id");
                    int user_id = rs.getInt("user_id");
                    User user = userFacade.getUser(user_id);
                    Carport carport = carportFacade.getCarport(carport_id);

                    Request_obj request;
                    request = new Request_obj(user, carport, status);
                    request.setRequest_id(request_id);
                    requestList.add(request);
                }
                return requestList;

            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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

