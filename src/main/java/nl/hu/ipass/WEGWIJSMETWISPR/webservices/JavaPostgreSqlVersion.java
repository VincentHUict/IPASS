package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlVersion {

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/WEGWIJSWISPR";
        String user = "postgres";
        String password = "geheim";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
        		Statement stmt = conn.createStatement();
        		ResultSet rs = stmt.executeQuery("SELECT VERSION()")) {
        	
        	if (rs.next()) {
        		System.out.println(rs.getString(1));
        	}
        } catch (SQLException exc) {
        	
        	Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
        	lgr.log(Level.SEVERE, exc.getMessage(), exc);
        }
	}
}
