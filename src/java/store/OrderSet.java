package store;

import java.util.*;

/**
 * klasa ta reperezentuje
 * kolekcje zamowien
 * @author Dominika Guziec
 * 
 *
 */
public class OrderSet {
    
	//kolekcja zawiera sie w arraylist
    private ArrayList<Order> set = null;
    
    /**
     * kosntruktor
     */
    public OrderSet() {
    	set = new ArrayList<Order>();
    } 
    
    /**
     * 
     * @param inSet ArrayList lista objektow inicjalizuje ta kolekcje
     */
    public OrderSet(ArrayList<Order> inSet) {
    	set = new ArrayList<Order>(inSet);
    }
    
    /**
     * getOrderAt zwraca zamowienia w okreslonej lokalizacji
     * @param index int indeks zwracanego zamowienia
     * @return Order
     */
    public Order getOrderAt(int index) {
    	return (Order)set.get(index);
    }
    
    /**
     * getOrderCount zwraca numer zamowienia
     * @return int
     */
    public int getOrderCount() {
    	return set.size();
    }
    
    /**
     * addOrder dodaje zamowienie do kolekcji
     * @param order Order order aby dodac do koolekcji
     */
    public void addOrder(Order order) {
    	set.add(order);
    }
    
    /**
     * removeOrderAt usuwanie zamowienia o wybranym indeksie i zwracanie
     * @param index int indeks usuwanego zamowienia
     * @return Order
     */
    public Order removeOrderAt(int index) {
    	return (Order)set.remove(index);
    }
    
    /**
     * removeOrder usuwanie wejsciowego zamowienia
     * @param order Order order aby usunac
     * @return boolean
     */
    public boolean removeOrder(Order order) {
    	return set.remove(order);
    }
    
}
