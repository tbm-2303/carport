package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessRequest extends CommandProtectedPage{


    public ProcessRequest(String pageToShow, String role) {
        super(pageToShow, role);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return pageToShow;
    }
}
