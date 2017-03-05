import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.OrderSet;
import store.Customer;
import store.Store;

/**
 * Klasa CustomerOrderHistoryController zarzadza
 * historia zamowien dostepnych dla 
 * danego klienta
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class CustomerOrderHistoryController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        //pobranie zalogowanego klienta aby pobrac jego historie zamowien
        Customer customer;
        customer = (Customer) session.getAttribute("customer");
        
        OrderSet orders = new OrderSet();
        
        try {
            Store store = new Store();
            orders = store.getOrderHistory(customer.getEmail());
            
            request.getSession().setAttribute("orders", orders);
            response.sendRedirect(request.getContextPath() + "/order_history.jsp");
        } catch(Exception e) {}

    }
}
