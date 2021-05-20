package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.ItemFacade;
import business.services.RequestFacade;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class SendRequest extends CommandUnprotectedPage {
    private ItemFacade itemFacade;
    private RequestFacade requestFacade;
    private CarportFacade carportFacade;
    private int start = 2400;


    public SendRequest(String pageToShow) {
        super(pageToShow);
        itemFacade = new ItemFacade(database);
        carportFacade = new CarportFacade(database);
        requestFacade = new RequestFacade(database);
    }

    private List<Item> CustomCarportRecipe(int length, int width, int shed_width, int shed_length) throws SQLException, UserException {
        List<Item> listy = new ArrayList<>();
        listy.add(itemFacade.SelectItemFromDB("Spær", 4800));// always there
        listy.add(itemFacade.SelectItemFromDB("Spær", length));// always there1
        listy.add(itemFacade.SelectItemFromDB("Spær", length));// always there2
        for (int i = 0; i < 6; i++) {
            listy.add(itemFacade.SelectItemFromDB("Spær", length));//always there6
        }
        for (int i = 0; i < 4; i++) {
            listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));//always there4
        }

        for (int i = 2400; i < 7510; i += 2500) {
            if (width > i + 2500) {
                for (int j = 0; j < 4; j++) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 3 for each 250cm width added
                }
            }
        }

        for (int i = 2400; i < 7510; i += 550) {
            if (width > (start + i)) {
                start = start + i;
                listy.add(itemFacade.SelectItemFromDB("Spær", length));// 1 for each 55cm length added
            }
        }
        if (length >= shed_length || width >= shed_width) {
            if (shed_length > 0 || shed_width > 0) {
                for (int i = 0; i < 4; i++) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));//3 if shed is added
                }
                if (shed_length > (length * 0.75)) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 1 additional if shed length is over threshold
                }
                if (shed_width > (width * 0.75)) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 1 additional if shed width is over threshold
                }
            }
        }

        return listy;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try {
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int shed_length = Integer.parseInt(request.getParameter("shed_length"));
            int shed_width = Integer.parseInt(request.getParameter("shed_width"));

            if (length < 240 || width < 240 || length > 780 || width > 750) {
                request.getSession().setAttribute("error", "somehow you messed up the input on the form. GJ");
            }
            HttpSession session = request.getSession();
            ServletContext servletContext = request.getServletContext();
            //itemlist
            List<Item> listy = CustomCarportRecipe(length, width, shed_width, shed_length);
            User user = (User) session.getAttribute("user");
            //price
            double price = 0;
            for (Item item : listy) {
                double itemprice = item.getPrice();
                price += itemprice;
            }
            //carport
            Carport carport = carportFacade.createCarportCustom(new Carport(price, length, width, shed_length, shed_width, "flat", "info"));
            carport.setItemList(listy);
            //request
            Request_obj request1 = requestFacade.createRequest(new Request_obj(user, carport, "requested"));
            //requestList
           /* List<Request_obj> requestyList = (List<Request_obj>) servletContext.getAttribute("requestList");

            if (requestyList == null) {
                requestyList = new ArrayList<>();
            }
            requestyList.add(request1);
            servletContext.setAttribute("requestList", requestyList);


            */
        } catch (InputMismatchException | SQLException e) {
            request.getSession().setAttribute("error", "somehow you messed up the input on the form");
        }
        return REDIRECT_INDICATOR + pageToShow;
    }

}
