package web.commands;

import business.entities.Carport;
import business.entities.RequestEntry;
import business.entities.Requesty;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowRequestpage extends CommandProtectedPage {


    public ShowRequestpage(String pageToShow, String role) {
        super(pageToShow, role);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
       //hent alle request fra db i en liste og læg den i applikationsscopet. RequestMapper -> List<Request> getAllRequestFromDb()
        //request.getServletContext.setAttribute("requestList", requestList);


        //



        return pageToShow;
    }
}
