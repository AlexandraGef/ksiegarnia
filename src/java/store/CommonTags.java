package store;

/**
 * Klasa CommonTags przechowuje funkcje wyswietlajace
 * nagłowek, form klienta, form zalogowanego klienta,
 * sciezke do cssa, form koszyka, form kategorii itd.
 *
 * @author Dominika Guziec
 * 
 */
public class CommonTags {
    /**
     * konstruktor
    */
    public CommonTags() {}
    
    /**
    * getHeader zwraca ustawienia dla nagłowka
    * @return String
    */
    public String getHeader() {
        String header = ""
                + "<table>"
                + "<tr>"
                + "<td>"
                + "<a href='index.jsp' style='color: white'>"
                + "<img src='images/logo.png' width='170' height='105'>"
                + "</a>"
                + "</td>"
                + "<td>"
                + getSpace(10)
                + "</td>"
                + "<td>"
                + "<form action='SearchController' method='post'>"
                + "<input type='text' name='searchString' size='50'>"
                + "</td>"
                + "<td>"
                + "<input type='image' src='images/search.png' alt='Search' height='26' width='73'>"
                + "</form>"
                + "</td>"
                + "</tr>"
                + "</table>";
        
        return header;
    }
    
    /**
     * getCustomerForm zwraca ustawienia dla klient form.
     * @return String
     */
    public String getCustomerForm() {
        String form = "<div id='userForm'>"
                + "<table align='right'>"
                + "<tr>"
                + "<td>"
                + "<a href='login.jsp'><image src='images/login.png' alt='Log in' height='26' width='65'></a>"
                + "<td>"
                + "<a href='signup.jsp'><image src='images/signup.png' alt='Log in' height='26' width='75'></a>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</div>";
        
        return form;
    }
    
    /**
     * getLoggedCustomerForm zwraca ustawienie dla strony logowania.
     * @param email String email klienta
     * @return String
     */
    public String getLoggedCustomerForm(String email) {
        String form = "<div id='userForm'>"
                + "<table align='right'>"
                + "<tr>"
                + "<td>"
                + "<font color='white'>"
                + email
                + "</font>"
                + "</td>"
                + "<td>"
                + "<form action='CustomerOrderHistoryController' method='post'>"
                + "<input type='image' src='images/cabinet.png' height='26' width='78'>"
                + "</form>"
                + "</td>"
                + "</tr>"
                + "<td>"
                + "<form action='CustomerLogoutController' method='post'>"
                + "<input type='image' src='images/logout.png' height='26' width='79'>"
                + "</form>"
                + "</td>"
                + "<tr>"
                + "</tr>"
                + "</table>"
                + "</div>";
        
        return form;
    }
    
    /**
     * getCss zwraca sciezke do cssa
     * @return String
     */
    public String getCss() {
        String css = "<link rel='stylesheet' type='text/css' href='layout.css'>";
        
        return css;
    }
    
    /**
     * addToCartForm zwraca ustawienia dodawania do koszyka
     * @param isbn String ISBN ksiazki zostaje dodane do koszyka
     * @return String
     */
    public String addToCartForm(String isbn) {
        String form = ""
                + "<form action='AddToCartController' method='post'>"
                + "<input type='image' src='images/add.png' alt='Add to cart' width='108' height='26'>"
                + "<input type='hidden' name='isbn' value='" + isbn + "'>"
                + "</form>";
        
        return form;
    }
    
    /**
     * getCartView zwara wyglad koszyka
     * @return String
     */
    public String getCartView() {
        String cartView = "<div align='center'>"
                + "<form action='cart_items.jsp' align='right'>"
                + "<input type='image' src='images/cart.png' alt='cart' height='70' width='70' align='center'>"
                + "</form>"
                + "</div>";
        
        return cartView;
    }
    
    /**
     * getCategoriesForm zwraca wyglad dla panelu bocznego kategorii
     * @return String
     */
    public String getCategoriesForm() {
        String form = ""
                + "<div id='categories'>"
                + "<h3 align='center'><font color='white'><i>Kategorie:</i></font></h3>"
                + "<form action='CategorizeController' method='post'>"
                + "<input type='submit' class='linkButton' value='Sztuka'>"
                + "<input type='hidden' name='category' value='Sztuka'>"
                + "</form>"
                + "<br>"
                + "<form action='CategorizeController' method='post'>"
                + "<input type='submit' class='linkButton' value='Informatyka'>"
                + "<input type='hidden' name='category' value='Informatyka'>"
                + "</form>"
                + "<br>"
                + "<form action='CategorizeController' method='post'>"
                + "<input type='submit' class='linkButton' value='Gotowanie'>"
                + "<input type='hidden' name='category' value='Gotowanie'>"
                + "</form>"
                + "<br>"
                + "<form action='CategorizeController' method='post'>"
                + "<input type='submit' class='linkButton' value='Fantastyka'>"
                + "<input type='hidden' name='category' value='Fantastyka'>"
                + "</form>"
                + "<br>"
                + "<form action='CategorizeController' method='post'>"
                + "<input type='submit' class='linkButton' value='Poradniki'>"
                + "<input type='hidden' name='category' value='Poradniki'>"
                + "</form>"
                + "<br>"
                + "<form action='CategorizeController' method='post'>"
                + "<input type='submit' class='linkButton' value='Dla dzieci'>"
                + "<input type='hidden' name='category' value='Dla dzieci'>"
                + "</form>"
                + "</div>";
        
        return form;
    }
    
    /**
     * getRowSpace zwaraca wyglad wierszy tabeli dla ksiazek
     * @param length int 
     * @return String
     */
    public String getRowSpace(int length) {
        String space = "";
        for (int i = 0; i < length; i++) {
            space += "<tr></tr>";
        }
        
        return space;
    }
    
    /**
     * getColumnSpace zwraca wyglad kolumn dla tabeli ksiazek
     * @param length int potrzebna dlugosc
     * @return String
     */
    public String getColumnSpace(int length) {
        String space = "<td>";
        for (int i = 0; i < length; i++) {
            space += "&nbsp;";
        }
        space += "</td>";
        
        return space;
    }
    
    /**
     * getSpace zwraca wielkosc przerwy
     * @param length int 
     * @return String
     */
    public String getSpace(int length) {
        String space = "";
        for (int i = 0; i < length; i++) {
            space += "&nbsp";
        }
        
        return space;
    }
    
    /**
     * getBreaks zwaraca podzialy wierszy.
     * @param length int
     * @return String
     */
    public String getBreaks(int length) {
        String space = "";
        for (int i = 0; i < length; i++) {
            space += "<br>";
        }
        
        return space;
    }
}
