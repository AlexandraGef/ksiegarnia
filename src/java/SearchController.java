import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * klasa SearchController odpowiadajaca
 * za wyszukiwanie
 * 
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class SearchController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchString = request.getParameter("searchString");
        
        // pasek wyszukiwania nie moze byc pusty
        if (searchString.equals("")) {
        	response.sendRedirect(request.getHeader("referer"));
        } else {
            BookSet books = new BookSet();

            try {
                Store store = new Store();
                books = store.search(searchString);
                
                request.getSession().setAttribute("books", books);
                response.sendRedirect(request.getContextPath() + "/search_results.jsp?searchString=" + searchString);
            } catch (Exception e) {}

        }
    }
    
}
