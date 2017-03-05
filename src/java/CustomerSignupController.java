import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.Store;

/**
 * klasa CustomerSignupController odpowiadajaca
 * za walidacje rejestracji
 * @author Dominika Guziec
 * 
 *
 */
public class CustomerSignupController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // zadne z pol nie moze byc puste
        if (email.equals("") || password.equals("")) {
            response.sendRedirect(request.getContextPath() + "/signup.jsp?email=" + 
                    email + "&err=Email i haslo nie moga byc puste !");
        } else {
            try {
                Store store = new Store();
                store.signupCustomer(email, password);
                response.sendRedirect(request.getContextPath() + "/index.jsp?greetings="
                        + "Dziekujemy za rejestracje ! Prosze sie zalogowac, aby moc zamawiac.");
            } catch(Exception e) {
            	// poinformowanie klienta o powtarzajacym sie emailu
                response.sendRedirect(request.getContextPath() + "/signup.jsp?email=" + 
                    email + "&err=Wybrany email jest juz zarejestrowany !");
            }
        }
    }
    
}
