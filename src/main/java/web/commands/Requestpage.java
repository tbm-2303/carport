package web.commands;

import business.entities.Carport;
import business.entities.Request;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.RequestMapper;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Requestpage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;

    public Requestpage(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            HttpSession session = request.getSession();
            List<Request_obj> requestList = requestFacade.getAllRequest2("requested");
            session.setAttribute("requestList22", requestList);
            return pageToShow;

        } catch (UserException exception) {
            request.getSession().setAttribute("error", "somehow you messed up the input on the form");
        }

        return pageToShow;
    }
}
