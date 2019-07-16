package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GebruikerPostgresDaoImpl extends PostgresBaseDao implements GebruikerDao {
	public String findRoleForGebruiker(String naam, String ww) {
		String result = null;
		try (Connection conn = super.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM medewerkersaccount " + "WHERE gebruikersnaam = '" + naam + "' and wachtwoord = '" + ww + "'");
			ResultSet dbResultSet = pstmt.executeQuery();

			while (dbResultSet.next()) {
				result = dbResultSet.getString("rol");
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		return result;
	}
}
