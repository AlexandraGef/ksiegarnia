package store;
import java.sql.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Properties;
import java.util.Random;

/**
 * klasa ta przechowuje
 * konfiguracje polaczenia
 * dostepu do bazy danych
 *
 * @author Dominika Guziec
 * 
 */
public class DBWrapper {
	
    private static int CONNECTION_RETRIES = 10;
    private static int QUERY_RETRIES = 10;
    private static int DEF_ISOLATION = Connection.TRANSACTION_READ_COMMITTED;
    
    private String dbUrl;
    private String password;
    private String username;
    private String jdbcClassName;
    private Connection dbCon;	

    private boolean hasError = false;
    private String errorString = null;
    private static DBWrapper myInstance = null;
    
    /**
     * konstruktor
     */
    private DBWrapper() 
    		throws Exception {
    	
    	connectAsDefaultLibrary();
    }

    /**
     * 
     * @param inUrl String url bazy danych
     * @param inJdbcClassName String przechowuje nazwe jdbc
     * @param inUserName String przechowuje nazwe do bazy danych
     * @param inPassWord String przechowuje haslo do bazy danych
     */
    private DBWrapper(String inUrl, String inJdbcClassName, String inUserName, String inPassWord) 
    		throws Exception {
    	
		dbUrl = inUrl;
		jdbcClassName = inJdbcClassName;
		username = inUserName;
		password = inPassWord;
		closeConnections();
		connect();
    }
    
    /**
     * connectAsDefaultLibrary()
     * tworzy polaczenie do bazy uzywajac ustawien parametrow polaczenia.
     * @return void
     */
    public void connectAsDefaultLibrary() 
    		throws Exception {
    	
    	dbUrl = "jdbc:postgresql://localhost:5433/ksiegarnia";
    	
        jdbcClassName = "org.postgresql.Driver";
        username = "postgres";
        password = "postgres";
		closeConnections();
		connect();
    }

    /**
     * boolean connect()
     *polaczenie do bazy uzywajace dostarczonych danych przez parametry w konstruktorze.
     * @return void
     */
    private void connect() 
    		throws Exception {
	
		// ustawienie nazyw klasy jdbc
		Driver driver = (Driver) Class.forName(jdbcClassName).newInstance();
		DriverManager.registerDriver(driver);
		
		dbCon = DriverManager.getConnection(dbUrl, username, password);
    }

    
    /**
     * closeConnections zamyka jakiekolwiek istniejeace juz polaczenie
     * @return void
     */
    private void closeConnections() 
    		throws Exception {
		
    	if (dbCon!=null) {
		    dbCon.close();
		}
    }

    /**
     * DBWrapper Instance()
     * pobiera pojedyncza instancje obiektu dbwrapper.
     * @return DBWrapper
     */
    public static DBWrapper Instance() 
    		throws Exception {
    	
		if (myInstance == null) {
			myInstance = new DBWrapper();
		    myInstance.connectAsDefaultLibrary();
		}
		
		return myInstance;
    }

    /**
     * komunikat getStmt()
     * ustawia automatyczne/manualne poziomy izolacji i deklaracji
     *
     * @param boolean isAutoCommit true jezeli deklaracja jest przy kazdej instrukcji sql
     * @param int isolationLevel Level true w tej instrukcji gdzie nalezy wykona trnasakcje
     */
    private Statement getStmt(boolean isAutoCommit, int isolationLevel)
    		throws SQLException {
    	
		// rozpoczyna transakcje w ustawionym stopniu izolacji
		dbCon.setAutoCommit(isAutoCommit);
		dbCon.setTransactionIsolation(isolationLevel);
		
		return dbCon.createStatement();
    }

    /**
     * ResultSet runQuery()
     * Wykonuje kwerendę i zwraca zestaw wyników.  
     *
     * @param String zawiera zapytania sql
     * @return ResultSet
     */
    public ResultSet runQuery(String sqlQuery) 
    		throws Exception {
    	
		Statement dbStatement = this.getStmt(true, DEF_ISOLATION);
		
		return dbStatement.executeQuery(sqlQuery);
		
    }
    
    /**
     * int runUpdate()
     * wykonuje aktualizacje i zwraca true jesli operacja jest zakonczona sukcesem  
     *
     * @param String przechowuje zapytanie sql
     * @return int numer aktualizowanego wiersza
     */    
    public int runUpdate(String sqlQuery) 
    		throws Exception {
	
        Statement dbStatement = getStmt(true, DEF_ISOLATION);
		int rowCount = dbStatement.executeUpdate(sqlQuery);
		dbStatement.close();
		
		return rowCount;
    }
    
    /**
     * ResultSet runChainedQuery()
     *
     *Wykonuje kwerendę łańcucha transakcji   
     *
     * @param <b>String</b> 
     * @param <b>String</b> przechopwuje stopien izolacji uruchomionej transakcji
     * @return ResultSet
     */  
    public ResultSet runChainedQuery(String sqlQuery, int isolationLevel) 
    		throws Exception {
	
        int retry = 0;
        boolean txnSuccess = false;
        Statement dbStatement = null;
        ResultSet resultSet = null;

		// polaczenie do bazy
		// ponowienie zapytania
        while (!txnSuccess && retry++ < QUERY_RETRIES) {
		    try { 
				dbStatement = getStmt(false, isolationLevel);
				// wykonuje kwerende
				resultSet = dbStatement.executeQuery( sqlQuery );
				// zatwierdzenie transakcji
				dbCon.commit();
				txnSuccess = true;
		    } catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				// jesli nastapi zakleszczenie , ponow probe po 10 msec
				if (se.getSQLState().equals("40P01")) {
				    Thread.sleep(10);
				} else {
				    dbCon.setTransactionIsolation(DEF_ISOLATION);
				    throw se;
				}
		    } catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
		    }
        }
        
        return resultSet;
    }
    
    /**
     * boolean runChainedUpdate()
     *  
     *
     * @param String[] 
     * @param String 
     * @return boolean true jesli transakcji konczy sie powodzeniem
     */  
    public boolean runChainedUpdate(String [] sqlQuery, int isolationLevel) 
    		throws Exception {
		
    	int retry = 0;
		boolean txnSuccess = false;
        Statement dbStatement = null;
	
        while (!txnSuccess && retry++ < QUERY_RETRIES) {
		    //rozpoczecie nowej transakcji
		    try	{
					dbStatement = getStmt(false, isolationLevel);
					
					// Dla każdej instrukcji SQL, należy wykonać aktualizację.
					for(int i = 0; i < sqlQuery.length; i++) {
						dbStatement.executeUpdate(sqlQuery[i]);
					}
					
					// zatwierdzenie transakcji i jej zamkniecie
					dbCon.commit();
					dbStatement.close();
					txnSuccess = true;
		    } catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				
				// jesli nastapi zakleszczenie powtorz po 10 msec
				if (se.getSQLState().equals("40P01")) {
				    Thread.sleep(10);
				} else {
				    dbCon.setTransactionIsolation(DEF_ISOLATION);
				    throw se;
				}
		    } catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
		    }
        }
        
        return txnSuccess;
    }
}
