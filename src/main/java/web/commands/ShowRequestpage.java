package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowRequestpage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;

    public ShowRequestpage(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();

        String string = "requested";
        List<Request> list = requestFacade.getAllRequest(string);// fetch all request(c_id,u_id,status) from db where status=requested
        List<Request_obj> list2 = new ArrayList<>();

        for (Request item: list) {
            Carport c = carportFacade.getCarport(item.getCarport_id());
            User u = userFacade.getUser(item.getUser_id());
            Request_obj request_obj = new Request_obj(u,c,string);
            list2.add(request_obj);
        }
        session.setAttribute("requestList22", list2);



        return pageToShow;
    }
}
