import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.Book;
import store.Store;

/**
 * Klasa BookDetailsController przechowuje
 * zyczenie klienta aby zobaczyc
 * szczegoly ksiazki
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class BookDetailsController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        Book book = new Book();
        
        try {
            Store store = new Store();
            book = store.getBookDetails(isbn);
            // Ustaw atrybut sesji książki, aby pobrac szczegoly.
            request.getSession().setAttribute("book", book);
            response.sendRedirect(request.getContextPath() + "/book_details.jsp?isbn=" + isbn);
        } catch(Exception e) {}
    }
    
}
