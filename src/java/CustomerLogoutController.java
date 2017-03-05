import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * klasa CustomerLogoutController odpowiada
 * za poprawne wylogowanie
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class CustomerLogoutController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// pobranie sesji
    	HttpSession session = request.getSession();
    	
    	// zakonczenie sesji
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
}
