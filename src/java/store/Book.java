package store;

/**
 * klasa reprezentujaca ksiazki
 * 
 * @author Dominika Guziec
 * 
 */
public class Book {
     
    private String isbn;	// isbn ksiazki
    private String title;	// tytul ksiazki
    private String description;	// opis
    private String publisher;	// wydawca
    private String publicationDate; // data wydania
    private String bookType; // typ okladki
    private float price; // cena
    
    private int quantity; // ilosc ksiazek (potrzebne do koszyka i zamowień)
    
    /**
     * konstruktor
     */
    public Book() {} 
    
    /**
     * 
     * @param inIsbn String ISBN
     * @param inTitle String tytul
     * @param inDescription String opis
     * @param inPublisher String wydawca
     * @param inPublicationDate String data wydania
     * @param inBookType String typ okladki
     * @param inPrice float cena
     */
    public Book(String inIsbn, String inTitle, String inDescription, 
            String inPublisher, String inPublicationDate, 
            String inBookType, Float inPrice) {
            
        
        isbn = inIsbn;
        title = inTitle;
        description = inDescription;
        publisher = inPublisher;
        publicationDate = inPublicationDate;
        bookType = inBookType;
        price = inPrice;
        
        quantity = 1;
    }
    
    /**
     * pobranie dla isbn
     * @return String
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * ustawienie dla isbn
     * @param inIsbn String wejsciowy parametr isbn
     * @return void
     */
    public void setIsbn(String inIsbn) {
        isbn = inIsbn;
    }
    
    /**
     * pobranie dla tytulu
     * @return String
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * ustawienie dla tytulu
     * @param inTitle String wejsciowy parametr tytulu
     * @return void
     */
    public void setTitle(String inTitle) {
        title = inTitle;
    }
    
    /**
     * pobranie dla opisu
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * ustawienie dla opisu
     * @param inDescription String wejsciowy parametr opisu
     * @return void
     */
    public void setDescription(String inDescription) {
        description = inDescription;
    }
    
    /**
     * porabie dla wydawcy
     * @return String
     */
    public String getPublisher() {
        return publisher;
    }
    
    /**
     * ustawienie dla wydawcy
     * @param inPublisher String wejsciowy parametr dla wydawcy
     * @return void
     */
    public void setPublisher(String inPublisher) {
        publisher = inPublisher;
    }
    
    /**
     * pobranie dla daty wydania
     * @return String
     */
    public String getPublicationDate() {
        return publicationDate;
    }
    
    /**
     * ustawienie dla daty wydania
     * @param inPublicationDate wejsciowy parametr daty wydania
     * @return void
     */
    public void setPublicationDate(String inPublicationDate) {
        publicationDate = inPublicationDate;
    }
    
    /**
     * porabie typu okladki
     * @return String
     */
    public String getBookType() {
        return bookType;
    }
    
    /**
     * ustawienie typu okladki
     * @param inBookType wejsciowy parametr dla okladki
     * @return void
     */
    public void setBookType(String inBookType) {
        bookType = inBookType;
    }
    
    /**
     * pobranie cena
     * @return float
     */
    public float getPrice() {
        return price;
    }
    
    /**
     * ustawienie dla cena
     * @param inPrice wejsciowy parametr ceny
     * @return void
     */
    public void setPrice(float inPrice) {
        price = inPrice;
    }
    
    /**
     * pobranie dla ilosci
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * ustawienie dla ilosci
     * @param inQuantity  wejsciowy parametr ilosci
     * @return void
     */
    public void setQuantity(int inQuantity) {
        quantity = inQuantity;
    }
}
