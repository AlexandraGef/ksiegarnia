package store;

/**
 * klasa reprezentujaca klienta
 * 
 * @author Dominika Guziec
 * 
 */
public class Customer {
	
    private String email; // email klienta
    private String password;	// haslo klienta
    
    /**
     * konstruktor
     */
    public Customer() {}
    
    /**
     * 
     * @param inEmail String 
     * @param inPassword String 
     */
    public Customer(String inEmail, String inPassword) {
        email = inEmail;
        password = inPassword;
    }
    
    /**
     * 
     * @return fString
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 
     * @param inEmail parametr wejsciowy email
     * @return void
     */
    public void setEmail(String inEmail) {
        email = inEmail;
    }
    
    /**
     * 
     * @return String
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 
     * @param inPassword parametr wejsciowy password
     * @return void
     */
    public void setPassword(String inPassword) {
        password = inPassword;
    }
    
}
