package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {
    public Connection connection = null;

    public PostgresBaseDao() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/WEGWIJSWISPR", "postgres", "geheim");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public Connection getConnection() {
    	Connection result = null;
    	
    	try {
    		InitialContext ic = new InitialContext();
    		DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
    		
    		result = ds.getConnection();
    	} catch (Exception exc) {
    		throw new RuntimeException(exc);
    	}
    	return result;
    }
}