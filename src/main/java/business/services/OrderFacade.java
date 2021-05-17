package business.services;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }
    public Order createOrder(Order order) throws UserException {
        orderMapper.createOrder(order);
        return order;
    }
    public void deleteOrder(int order_id){

    }
    public void updateOrderStatus(int order_id){

    }
    public void updateOrderDimensions(int width,int length, int shed_length, int shed_width){

    }



}
