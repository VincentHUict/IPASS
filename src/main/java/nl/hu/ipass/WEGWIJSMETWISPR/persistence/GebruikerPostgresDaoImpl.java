package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GebruikerPostgresDaoImpl extends PostgresBaseDao implements GebruikerDao {
	public String findRoleForGebruiker(String name, String pass) {
		String result = null;
		try (Connection conn = super.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM medewerkersaccount " + "WHERE gebruikersnaam = '" + name + "' AND wachtwoord = '" + pass + "'");
			ResultSet dbResultSet = pstmt.executeQuery();

			while (dbResultSet.next()) {
				result = dbResultSet.getString("role");
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		return result;
	}

	@Override
	public String getGebruikersnaam(String role) {
		String result = null;
		try (Connection conn = super.getConnection()) {
			String query = "SELECT gebruikersnaam FROM gebruikersaccount WHERE rol = '" + role + "'";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				result = dbResultSet.getString("gebruikersnaam");
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		} return result;
	}
}
