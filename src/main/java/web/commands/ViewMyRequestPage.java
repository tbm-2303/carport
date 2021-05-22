package web.commands;

import business.entities.Carport;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;
import business.services.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewMyRequestPage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;
    Util util;

    public ViewMyRequestPage(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        //carportFacade = new CarportFacade(database);
        //userFacade = new UserFacade(database);
        util = new Util();

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Request_obj> requestList = requestFacade.getAllRequest3(user.getId(), "requested");  //**** problem ***
            List<Request_obj> requestList2 = new ArrayList<>();
            for (Request_obj item: requestList) {
                Carport carport = item.getCarport();
                item.setItemList(util.getItemList(carport.getShed_length(), carport.getWidth(), carport.getShed_length(), carport.getShed_width()));
                requestList2.add(item);
            }
            
            request.setAttribute("requestList_customer", requestList2);

            return pageToShow;

        } catch (UserException e) {
            request.getSession().setAttribute("error", "database error: no request found");
            return "index";
        }
    }
}
