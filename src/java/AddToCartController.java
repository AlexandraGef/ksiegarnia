import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * klasa AddToCartController przechowuje
 * sesje klienta aby dodac 
 * do koszyka
 * 
 * 
 * 
 *
 */
public class AddToCartController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        // pobranie sesji aby dodac do koszyka
        HttpSession session = request.getSession();
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        try {
            Store store = new Store();
            store.addBookToCart(cart, isbn);
            request.getSession().setAttribute("cart", cart);
            response.sendRedirect(request.getHeader("referer"));
        } catch(Exception e) {}

    }
    
}
