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
					"SELECT * FROM gebruikersaccount WHERE gebruikersnaam = ? AND wachtwoord = ? AND rol = ?;");
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
			String query = "SELECT gebruikersnaam FROM gebruikersaccount WHERE rol = '" + role + "';";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				result = dbResultSet.getString("gebruikersnaam");
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
		} return result;
	}

	@Override
	public boolean saveGebruiker(String gebruikersnaam, String wachtwoord) {
		try (Connection conn = super.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
									"INSERT INTO gebruikersaccount(gebruikersnaam, wachtwoord, rol) VALUES(?, ?, ?);");
			pstmt.execute();
			return true;
		} catch (SQLException exc) {
			System.out.println("Gebruikersnaam en/of wachtwoord al in gebruik!");
			System.out.println(exc);
			return false;
		}
	}
}
