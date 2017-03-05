package store;

import java.util.*;

/**
 * Class BookSet reprezentuje
 * kolekcję książek.
 * 
 * @author Dominika Guziec
 * 
 */
public class BookSet {
	
	// kolekcja ksiązek jest trzymana w arraylist
    private ArrayList<Book> set = null;
    
    /**
     * konstruktor class BookSet
     */
    public BookSet() {
    	set = new ArrayList<Book>();
    } 
    
    /**
     * 	
     * @param inSet ArrayList w tym miejscu jest inicjalizowana lista.
     */
    public BookSet(ArrayList<Book> inSet) {
    	set = new ArrayList<Book>(inSet);
    }
    
    /**
     * getBookAt zwarca ksiazkę w okreslonej lokalizacji.
     * @param index int indeks zwracanej ksiazki
     * @return Book
     */
    public Book getBookAt(int index) {
    	return (Book)set.get(index);
    }
    
    /**
     * getBookCount zwraca numer ksiazki.
     * @return int
     */
    public int getBookCount() {
    	return set.size();
    }
    
    /**
     * addBook dodaje ksiazke do kolekcji.
     * @param book Book book aby dodac do kolekcji
     */
    public void addBook(Book book) {
    	set.add(book);
    }
    
    /**
     * removeBookAt usuwa ksiazke o podanym indeksie i zwraca wynik.
     * @param index int indeks ksiazki ktora ma byc usunieta i zwrocona
     * @return Book
     */
    public Book removeBookAt(int index) {
    	return (Book)set.remove(index);
    }

    /**
     * removeBook usuwa wejsciowe ksiazki.
     * @param book Book book aby usunac
     * @return boolean
     */
    public boolean removeBook(Book book) {
    	return set.remove(book);
    }
    
}
