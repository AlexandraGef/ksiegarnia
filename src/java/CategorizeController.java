import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 *obsluga kategorii
 * @author Dominika Guziec
 *
 */
public class CategorizeController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String category = request.getParameter("category");
        BookSet categorizedBooks = new BookSet();

        try {
            Store store = new Store();
            categorizedBooks = store.categorize(category);
            // ustawia atrybut categorizedBooks dla wybranej kategorii
            request.getSession().setAttribute("categorizedBooks", categorizedBooks);
            response.sendRedirect(request.getContextPath() + "/categorized_books.jsp?category=" + category);
        } catch (Exception e) {}

    }
    
}
