package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;
import business.services.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        util = new Util(database);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Request_obj> requestList = requestFacade.getAllRequest3(user.getId(), "requested");
            List<Request_obj> requestList2 = new ArrayList<>();

            if (!requestList.isEmpty()) {
                for (Request_obj item : requestList) {
                    Carport carport = item.getCarport();
                    List<Item> itemlist = util.CustomCarportRecipe(carport.getLength(), carport.getWidth(), carport.getShed_width(), carport.getShed_length());
                    item.setItemList(itemlist);
                    requestList2.add(item);
                }
            }
            request.setAttribute("requestList_customer", requestList2);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", "database error: no request found");
            return "index";
        }
    }
}
