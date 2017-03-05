import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * klasa DeleteBookFromCartController odpowiada
 * za usuniecie ksiazki
 * z koszyka
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class DeleteBookFromCartController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        // pobranie koszyka danej sesji
        HttpSession session = request.getSession();
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        try {
            Store store = new Store();
            store.deleteBookFromCart(cart, isbn);
            
            session.setAttribute("cart", cart);
            response.sendRedirect(request.getHeader("referer"));
        } catch(Exception e) {}
        
    }
    
}
