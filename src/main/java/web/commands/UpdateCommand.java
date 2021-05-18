
package web.commands;


public class UpdateCommand extends CommandProtectedPage {


    public UpdateCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }
/*

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();
        List<Requesty> RL1 = (List<Requesty>) servletContext.getAttribute("requestList");
        String[] pricelist = request.getParameterValues("price");
        List<Double> pricelistInt = new ArrayList<>();

        for (String price : pricelist) {
            pricelistInt.add(Double.parseDouble(price));
        }
        for (int i = 0; i < RL1.size(); ++i) {
            RL1.get(i).setPrice(pricelistInt.get(i));
        }
        String remove = request.getParameter("remove");
        if (remove != null) {
            RL1.remove(Integer.parseInt(remove));
        }
        String confirm = request.getParameter("confirm");
        if (confirm != null) {
            //Requesty request2 = RL1.get(Integer.parseInt(confirm));
            //List<Requesty> offerlist = (List<Requesty>) request.getServletContext().getAttribute("offerList");
           //if (offerlist == null) {
             //   offerlist = new ArrayList<>();
            //}
           // offerlist.add(request2);
           // servletContext.setAttribute("offerList", offerlist);
            //RL1.remove(Integer.parseInt(confirm));
        }
        request.getServletContext().setAttribute("requestList", RL1);
        return pageToShow;
    }

     */

}


