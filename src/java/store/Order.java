package store;

/**
 * klasa reperentuje zamowienia
 * 
 * @author Dominika Guziec
 * 
 */
public class Order {

    private int orderId; // numer zamowienia
    private String email; // email klienta ktory dokonuje zamowienia
    private String firstName; // imie klienta
    private String lastName; // nazwisko klienta
    private String address; // adress klienta
    private String phone; //numer telefonu klienta
    private String deliveryMethod; // metoda dostawy
    private String orderDate; // data zlecenia zamowienia
    private float orderCost; // calkowity koszt zamowienia z przesylka
    
    /**
     * konstruktor
     */
    public Order() {}
    
    /**
     * 
     * @param inOrderId int 
     * @param inEmail String 
     * @param inFirstName String 
     * @param inLastName String 
     * @param inAddress String 
     * @param inPhone String 
     * @param inDeliveryMethod String 
     * @param inOrderDate String date 
     * @param inOrderCost float 
     */
    public Order(int inOrderId, String inEmail, String inFirstName, 
            String inLastName, String inAddress, String inPhone, 
            String inDeliveryMethod, String inOrderDate, Float inOrderCost) {
        
        orderId = inOrderId;
        email = inEmail;
        firstName = inFirstName;
        lastName = inLastName;
        address = inAddress;
        phone = inPhone;
        deliveryMethod = inDeliveryMethod;
        orderDate = inOrderDate;
        orderCost = inOrderCost;
    }
    
    /**
     * 
     * @return String
     */
    public int getOrderId() {
        return orderId;
    }
    
    /**
     * 
     * @param inOrderId int
     * @retrn void
     */
    public void setOrderId(int inOrderId) {
        orderId = inOrderId;
    }
    
    /**
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 
     * @param inEmail String 
     * @return void
     */
    public void setEmail(String inEmail) {
        email = inEmail;
    }
    
    /**
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     *
     * @param inFirstName String 
     * @return void
     */
    public void setFirstName(String inFirstName) {
        firstName = inFirstName;
    }
    
    /**
     * 
     * @return String
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * 
     * @param inLastName String 
     * @return void
     */
    public void setLastName(String inLastName) {
        lastName = inLastName;
    }
    
    /**
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * 
     * @param inAddress String 
     * @return void
     */
    public void setAddress(String inAddress) {
        address = inAddress;
    }
    
    /**
     * 
     * @return String
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * 
     * @param inPhone String 
     * @return void
     */
    public void setPhone(String inPhone) {
        phone = inPhone;
    }
    
    /**
     * 
     * @return String
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }
    
    /**
     *
     * @param inDeliveryMethod String
     * @return void
     */
    public void setDeliveryMethod(String inDeliveryMethod) {
        deliveryMethod = inDeliveryMethod;
    }
    
    /**
     * 
     * @return String
     */
    public String getOrderDate() {
        return orderDate;
    }
    
    /**
     * 
     * @param inOrderDate String 
     * @return void
     */
    public void setOrderDate(String inOrderDate) {
        orderDate = inOrderDate;
    }
    
    /**
     * 
     * @return float
     */
    public float getOrderCost() {
        return orderCost;
    }
    
    /**
     *
     * @param inOrderCost float
     * @return void
     */
    public void setOrderCost(float inOrderCost) {
        orderCost = inOrderCost;
    }
    
}
