import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.Customer;
import store.Store;

/**
 * klasa CustomerLoginController zawiera
 * walidacje logowania
 * 
 * 
 * @author Dominika Guziec
 *
 *
 */
public class CustomerLoginController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // sprawdzenie czy zadne z pol nie jest puste
        if (email.equals("") || password.equals("")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?email=" + 
                    email + "Email i haslo nie moga byc puste !");
        } else {
            Customer customer = null;

            try {
            		Store store = new Store();
	                customer = store.validateCustomer(email, password);
	                request.getSession().setAttribute("customer", customer);
	                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch(Exception e) {
            	// poinformowanie klienta o niepoprawnych danych
                response.sendRedirect(request.getContextPath() + "/login.jsp?email=" + 
                            email + "&err=Niepoprawne haslo lub email !");
            }
        }
    }
    
}
