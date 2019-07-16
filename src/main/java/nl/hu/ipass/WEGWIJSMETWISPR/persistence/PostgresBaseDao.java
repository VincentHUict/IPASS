package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class PostgresBaseDao {
    private static DataSource connectionPool;
    
    public PostgresBaseDao() {
    	if (connectionPool == null) {
    		try {
    			final String DATABASE_URL = System.getenv("DATABASE_URL");
    			
//    			if (DATABASE_URL != null) { //heroku
//    				URI dbUri = new URI(DATABASE_URL);
//    				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
//    				BasicDataSource bds = new BasicDataSource();
//    				
//    				connectionPool = bds;
//    			} else { //lokaal
//    				InitialContext ic = new InitialContext();
//    				connectionPool = (DataSource)ic.lookup("java:comp/env/jdbc/PostgresDS");
//    			}
    		} catch (Exception exc) {
    			throw new RuntimeException(exc);
    		}
    	}
    }
    
    public Connection getConnection() {
    	Connection result = null;
    	
    	try {
    		InitialContext ic = new InitialContext();
    		DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/PostgresDS");
    		result = ds.getConnection();
    	} catch (Exception exc) {
    		throw new RuntimeException(exc);
    	}
    	return result;
    }
   
	public void closeConnection() throws SQLException {
		Connection myConn = getConnection();
		myConn.close();
	}
}
