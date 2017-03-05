package store;

import java.sql.*;

/**
 * klasa reprezentuje funkcjonalnosci sklepu
 * 
 * @author Dominika Guziec
 * 
 *
 */
public class Store {
	
	
    private DBWrapper myConnection = null;
    
    /**
     * konstruktor
     * @throws Exception
     */
    public Store() 
    		throws Exception {
	
    	myConnection = DBWrapper.Instance();
    }
    
    /**
     * wyszukiwanie ksiazek po tytule, imieniu i nazwisku autora.
     * @param searchString
     * @return books BookSet kolekcja zanlezionych ksiazek.
     * @throws Exception
     */
    public BookSet search(String searchString) 
            throws Exception {
        
        BookSet books = new BookSet();
        
        String query = "SELECT DISTINCT isbn, title, description, publisher, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price "
                + "FROM BOOKAUTHORS "
                + "NATURAL JOIN BOOKS "
                + "NATURAL JOIN AUTHORS "
                + "WHERE LOWER(title) LIKE LOWER('%" + searchString + "%') OR "
                + "LOWER(first_name) LIKE LOWER('%" + searchString + "%') OR "
                + "LOWER(last_name) LIKE LOWER('%" + searchString + "%') OR "
                + "LOWER(CONCAT(first_name, ' ' || last_name)) LIKE LOWER('%" + searchString+ "%') OR "
                + "LOWER(CONCAT(last_name, ' ' || first_name)) LIKE LOWER('%" + searchString+ "%') OR "
                + "LOWER(CONCAT(first_name, last_name)) LIKE LOWER('%" + searchString+ "%') OR "
                + "LOWER(CONCAT(last_name, first_name)) LIKE LOWER('%" + searchString+ "%') "
                + "ORDER BY title";
                
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Book temp = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                    r.getString("publisher"), r.getString("publication_date"), 
                    r.getString("book_type"), r.getFloat("price"));
            
            books.addBook(temp);
        }
        
        return books;
    }
    
    /**
     * getLatestBooks wyszukuje ksiazki opublikowane w 2015 lub pozniej.
     * @return books BookSet kolekacjka znalezionych ksiazek.
     * @throws Exception
     */
    public BookSet getLatestBooks() 
            throws Exception {
        BookSet books = new BookSet();
        
        String query = "SELECT isbn, title, description, publisher, publication_date as pub_date, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price FROM BOOKS "
                + "WHERE publication_date >= TO_DATE('01-Jan-2015', 'dd-Mon-yyyy')";
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Book temp = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                    r.getString("publisher"), r.getString("publication_date"), 
                    r.getString("book_type"), r.getFloat("price"));
            
            books.addBook(temp);
        }
        
        return books;
    }
    
    /**
     * categorize zwaraca ksiazki o okreslonej kategorii
     * @param category String wybrana kategoria ksiazek.
     * @return books BookSet koleckcja ksiazek o wybranej kategorii.
     * @throws Exception
     */
    public BookSet categorize(String category) 
            throws Exception {
        
        BookSet books = new BookSet();
        
        String query = "SELECT isbn, title, description, publisher, publication_date as pub_date, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price FROM BOOKCATEGORIES "
                + "NATURAL JOIN BOOKS "
                + "WHERE category_name = '" + category + "' "
                + "ORDER BY title";
                
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Book temp = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                    r.getString("publisher"), r.getString("publication_date"), 
                    r.getString("book_type"), r.getFloat("price"));
            
            books.addBook(temp);
        }
        
        return books;
    }
    
    /**
     * getBookDetails zwraca isbn ksiazki
     * @param isbn String ISBN 
     * @return book Book book 
     * @throws Exception
     */
    public Book getBookDetails(String isbn) 
            throws Exception {
        
        String query = "SELECT isbn, title, description, publisher, publication_date as pub_date, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price FROM BOOKS "
                + "WHERE isbn = '" + isbn + "'";
        ResultSet r = myConnection.runQuery(query);
        r.next();
        Book book = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                r.getString("publisher"), r.getString("publication_date"), 
                r.getString("book_type"), r.getFloat("price"));
        r.close();
        
        return book;
    }
    
    /**
     * getBookCategories zwraca nazwe kategorii do ktorej nalezy ksiazka
     * @param isbn String ISBN ksiazki.
     * @return String
     * @throws Exception
     */
    public String getBookCategories(String isbn)
            throws Exception {
        
        String categories = "";
        String query = "SELECT category_name FROM BOOKCATEGORIES WHERE isbn = '" + isbn + "'";
        ResultSet result = myConnection.runQuery(query);
        ResultSet count = myConnection.runQuery("SELECT COUNT(isbn) AS num FROM BOOKCATEGORIES WHERE isbn ='" + isbn + "'");
        count.next();
        int num = count.getInt("num");
        count.close();
        
        for (int i = 0; i < num - 1 ; i++) {
            result.next();
            categories += result.getString("category_name") + ", ";
        }
        
        result.next();
        categories += result.getString("category_name");
        
        return categories;
    }
    
    /**
     * getBookAuthors zwraca nazwe autora
     * @param isbn String ISBN ksiazki
     * @return String
     * @throws Exception
     */
    public String getBookAuthors(String isbn)
            throws Exception {
        
        String authors = "";
        String query = "SELECT first_name, last_name FROM BOOKAUTHORS "
                + "NATURAL JOIN AUTHORS WHERE isbn = '" + isbn + "'";
        ResultSet result = myConnection.runQuery(query);
        ResultSet count = myConnection.runQuery("SELECT COUNT(isbn) AS num FROM BOOKAUTHORS WHERE isbn ='" + isbn + "'");
        count.next();
        int num = count.getInt("num");
        count.close();
        
        for (int i = 0; i < num - 1 ; i++) {
            result.next();
            authors += result.getString("first_name") + " " + 
                    result.getString("last_name") + ", ";
        }
        
        result.next();
        authors += result.getString("first_name") + " " + 
                    result.getString("last_name");
        
        return authors;
    }
    
    /**
     * addBookToCart dodanie ksiazki do koszyka
     * @param cart BookSet koszyk do ktorego ma byc dodana ksiazka
     * @param isbn String ISBN ksiazki
     * @throws Exception
     */
    public void addBookToCart(BookSet cart, String isbn) 
            throws Exception{
        
        boolean check = false;
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            if (cart.getBookAt(i).getIsbn().equals(isbn)) {
                check = true;
                break;
            }
        }
        
        if (!check) {
            cart.addBook(getBookDetails(isbn));
        }
    }
    
    /**
     * deleteBookFromCart usuwanie ksiazki z koszyka
     * @param cart BookSet koszyk z ktorego ksiazka ma yc usunieta
     * @param isbn String ISBN ksiazki
     * @throws Exception
     */
    public void deleteBookFromCart(BookSet cart, String isbn) 
            throws Exception {
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            if (cart.getBookAt(i).getIsbn().equals(isbn)) {
                cart.removeBookAt(i);
                break;
            }
        }
    }
    
    /**
     * validateCustomer walidacja klienta ktory sie loguje
     * @param email String 
     * @param password String 
     * @return customer Customer customer sprawdza poprawnosc danych
     * @throws Exception
     */
    public Customer validateCustomer(String email, String password) 
            throws Exception {
        
        String checkQuery = "SELECT * FROM CUSTOMERS WHERE email = '" + email + "' AND "
                + "password = '" + password + "'";
        
        Customer customer = null;
        ResultSet r = myConnection.runQuery(checkQuery);
        
        if (r == null) {
            return null;
        } else {
            r.next();
            customer = new Customer(r.getString("email"), r.getString("password"));
            r.close();
            
            return customer;
        }
    }
    
    /**
     * signupCustomer rejestracja nowego klienta
     * @param email String 
     * @param password String 
     * @throws Exception
     */
    public void signupCustomer(String email, String password) 
            throws Exception {
        
        String query = "INSERT INTO CUSTOMERS VALUES ('" + email + "', '" + password + "')";
        int r = myConnection.runUpdate(query);
    }
    
    /**
     * addOrder dodanie nowego zamowienia
     * @param cart Cart cart zawiera ksiazki
     * @param email String 
     * @param firstName String 
     * @param lastName String 
     * @param address String 
     * @param phone String 
     * @param deliveryMethod String 
     * @throws Exception
     */
    public void addOrder(BookSet cart, String email, String firstName, 
            String lastName, String address, String phone, String deliveryMethod) 
            throws Exception {
        
        float orderCost = 0;
        for (int i = 0; i < cart.getBookCount(); i++) {
            orderCost += cart.getBookAt(i).getPrice() * cart.getBookAt(i).getQuantity();
        }
        
        if (deliveryMethod.equals("courier")) {
            orderCost += 5.0;
        }
        
        String orderQuery = "INSERT INTO ORDERS VALUES "
                + "(nextval('order_numbers'), '"+email+"', "
                + "'" + firstName + "', '" + lastName + "', '" + address + "', '" + phone + "', "
                + "'" + deliveryMethod + "', current_date, " + orderCost + ")";

        
        int r1 = myConnection.runUpdate(orderQuery);
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            String orderItemsQuery = "INSERT INTO ORDERITEMS VALUES ("
                    + "currval('order_numbers'), '" + cart.getBookAt(i).getIsbn() + "', "
                    + cart.getBookAt(i).getQuantity() + ""
                    + ")";
            
            int r2 = myConnection.runUpdate(orderItemsQuery);
        }
    }
    
    /**
     * getOrderHistory zwraca kolecke zamowienien dla danego zalogowanego klienta
     * @param email String 
     * @return orders OrderSet zamoweinia dla zalogowanego klienta
     * @throws Exception
     */
    public OrderSet getOrderHistory(String email) 
            throws Exception {
        
        OrderSet orders = new OrderSet();
        
        String query = "SELECT order_id, email, first_name, last_name, "
                + "address, phone, delivery_method, TO_CHAR(order_date, 'Month dd, yyyy') as order_date, "
                + "order_cost FROM ORDERS WHERE email = '" + email + "'";
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Order temp = new Order(r.getInt("order_id"), r.getString("email"), 
                    r.getString("first_name"), r.getString("last_name"), 
                    r.getString("address"), r.getString("phone"), 
                    r.getString("delivery_method"), r.getString("order_date"), 
                    r.getFloat("order_cost"));
            
            orders.addOrder(temp);
        }
        
        return orders;
    }
    
    /**
     * getOrderedBooks zwraca htmlowska reperezentacje ksiazek w zamowieniu.
     * @param orderId int numer zamowienia
     * @return String
     * @throws Exception
     */
    public String getOrderedBooks(int orderId) 
            throws Exception{
    	
        String books = "<font size='2'>";
        
        String query = "SELECT title, isbn, quantity "
                + "FROM ORDERITEMS "
                + "NATURAL JOIN ORDERS "
                + "NATURAL JOIN BOOKS "
                + "WHERE order_id = " + orderId + "";
        
        ResultSet r = myConnection.runQuery(query);
        
        int number = 1;
        while (r.next()) {
            books += number + ". <i>" + r.getString("title") + "</i> by " 
                    + getBookAuthors(r.getString("isbn")) + "<br>" + "&nbsp;&nbsp;&nbsp;&nbsp;Quantity: " 
                    + r.getString("quantity") + "<br>";
            number++;
        }
        
        books += "</font>";
        
        return books;
    }
}   
