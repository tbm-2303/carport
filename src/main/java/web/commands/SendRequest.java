package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.ItemFacade;
import business.services.OrderFacade;

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
    private OrderFacade orderFacade;
    private int start = 240;


    public SendRequest(String pageToShow) {
        super(pageToShow);
        itemFacade = new ItemFacade(database);
        orderFacade = new OrderFacade(database);
    }

    private List<Item> CustomCarportRecipe(int length, int width, int shed_width, int shed_length) throws SQLException, UserException {
        List<Item> listy = new ArrayList<>();
        listy.add(itemFacade.SelectItemFromDB("Spærtræ", 480));// always there
        listy.add(itemFacade.SelectItemFromDB("Spærtræ", length));// always there1
        listy.add(itemFacade.SelectItemFromDB("Spærtræ", length));// always there2
        for (int i = 0; i < 6; i++) {
            listy.add(itemFacade.SelectItemFromDB("Spærtræ", length));//always there6
        }
        for (int i = 0; i < 4; i++) {
            listy.add(itemFacade.SelectItemFromDB("Stolpe", 300));//always there4
        }

        for (int i = 240; i < 751; i += 250) {
            if (width > i + 250) {
                for (int j = 0; j < 4; j++) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 300));// 3 for each 250cm width added
                }
            }
        }

        for (int i = 240; i < 751; i += 55) {
            if (width > (start + i)) {
                start = start + i;
                listy.add(itemFacade.SelectItemFromDB("Spærtræ", length));// 1 for each 55cm length added
            }
        }
        if (shed_length > 0 || shed_width > 0) {
            for (int i = 0; i < 4; i++) {
                listy.add(itemFacade.SelectItemFromDB("Stolpe", 300));//3 if shed is added
            }
            if (shed_length > (length * 0.75)) {
                listy.add(itemFacade.SelectItemFromDB("Stolpe", 300));// 1 additional if shed length is over threshold
            }
            if (shed_width > (width * 0.75)) {
                listy.add(itemFacade.SelectItemFromDB("Stolpe", 300));// 1 additional if shed width is over threshold
            }
        }
        return listy;
    }

    private void CarportCategory(int width, int length, int shed_length, int shed_width) throws SQLException, UserException {
        if (shed_width == 0 || shed_length == 0) {

            if (length >= 240 && length <= 340) {
                if (width >= 240 && width <= 340) {//small (1)
                    CustomCarportRecipe(length, width, shed_width, shed_length);
                } else if (width >= 341 && width <= 440) {//medium (2)
                    CustomCarportRecipe(length, width, shed_width, shed_length);
                } else if (width >= 441 && width <= 540) {//large (3)
                    CustomCarportRecipe(length, width, shed_width, shed_length);

                } else if (width >= 541 && width <= 640) {//big (4)
                } else if (width >= 641 && width <= 750) {//huge (5)
                }
            } else if (length >= 341 && length <= 440) {
                if (width >= 240 && width <= 340) {// (6)
                } else if (width >= 341 && width <= 440) {// (7)
                } else if (width >= 441 && width <= 540) {// (8)
                } else if (width >= 541 && width <= 640) {// (9)
                } else if (width >= 641 && width <= 750) {// (10)
                }
            } else if (length >= 441 && length <= 540) {
                if (width >= 240 && width <= 340) {
                } else if (width >= 341 && width <= 440) { //(11)
                } else if (width >= 441 && width <= 540) {//(12)
                } else if (width >= 541 && width <= 640) {//(13)
                } else if (width >= 641 && width <= 750) {//(14)
                }
            } else if (length >= 541 && length <= 640) {
                if (width >= 240 && width <= 340) {//(15)
                } else if (width >= 341 && width <= 440) {//(16)
                } else if (width >= 441 && width <= 540) {//(17)
                } else if (width >= 541 && width <= 640) {//(18)
                } else if (width >= 641 && width <= 750) {//(19)
                }
            } else if (length >= 641 && length <= 780) {
                if (width >= 240 && width <= 340) {//(20)
                } else if (width >= 341 && width <= 440) {//(21)
                } else if (width >= 441 && width <= 540) {//(22)
                } else if (width >= 541 && width <= 640) {//(23)
                } else if (width >= 641 && width <= 750) {//(24)
                }
            }
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try {
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int shed_length = Integer.parseInt(request.getParameter("shed_length"));
            int shed_width = Integer.parseInt(request.getParameter("shed_width"));
            HttpSession session = request.getSession();
            ServletContext servletContext = request.getServletContext();

            if (length < 240 || width < 240 || length > 780 || width > 750) {
                request.getSession().setAttribute("error", "somehow you messed up the input on the form");
            }
            List<Item> listy2 = CustomCarportRecipe(length, width, shed_width, shed_length);// generates itemlist
            User user = (User) session.getAttribute("user");
            Requesty request1 = new Requesty(width, length, shed_length, shed_width, user, listy2);

            List<Requesty> requestyList;
            requestyList = (List<Requesty>) request.getSession().getAttribute("requestList");
            if (requestyList == null) {
                requestyList = new ArrayList<>();
            }
            requestyList.add(request1);
            servletContext.setAttribute("requestList", requestyList);

        } catch (InputMismatchException | SQLException e) {
            request.getSession().setAttribute("error", "somehow you messed up the input on the form");
        }
        return REDIRECT_INDICATOR + pageToShow;
    }

}
