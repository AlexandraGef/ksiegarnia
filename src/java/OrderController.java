import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Customer;
import store.Store;

/**
 * klasa OrderController odpowiada
 * za prawidlowa obsluge
 * zamowien
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class OrderController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // pobranie koszyka danej sesji
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        // pobrania danych klienta dane sesji
        Customer customer;
        customer = (Customer) session.getAttribute("customer");
        
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String deliveryMethod = request.getParameter("delivery_method");
        
        try {
            Store store = new Store();
            store.addOrder(cart, customer.getEmail(), firstName, lastName, 
                    address, phone, deliveryMethod);
            
            request.getSession().setAttribute("cart", cart);
            request.getSession().setAttribute("customer", customer);
            response.sendRedirect(request.getContextPath() + 
                "/index.jsp?thanks=Twoje zamowienie zostalo dodane. "
                + "Dziekujemy !<br> Mozesz sprawdzic swoje zamowienia naciskajac przycisk `Zamowienia`.");
            
        } catch(Exception e) {}
    }
    
}
