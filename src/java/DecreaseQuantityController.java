import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;

/**
 * klasa DecreaseQuantityController odpowiada
 * za zmiejszenie ilosci
 * ksiazek w koszyku
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class DecreaseQuantityController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        // pobranie koszyka danej sesji
        HttpSession session = request.getSession();
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            if (cart.getBookAt(i).getIsbn().equals(isbn) && cart.getBookAt(i).getQuantity() > 1) {
                cart.getBookAt(i).setQuantity(cart.getBookAt(i).getQuantity() - 1);
                break;
            }
        }
        
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getHeader("referer"));
    }
    
}
