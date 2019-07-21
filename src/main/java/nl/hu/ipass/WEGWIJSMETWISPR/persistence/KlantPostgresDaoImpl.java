package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Klant;

public class KlantPostgresDaoImpl extends PostgresBaseDao implements KlantDao {

	@Override
	public List<Klant> findAll() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT * FROM klant;").executeQuery());
	}

	private List<Klant> getList(ResultSet resultSet) throws SQLException {
		List<Klant> klanten = new ArrayList<>();
		while (resultSet.next()) {
			klanten.add(toKlant(resultSet));
		}
		return klanten;
	}
	
	private Klant toKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getString("ID"), resultSet.getString("NAAM"));
	}
	
	@Override
	public boolean save(Klant klant) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("INSERT INTO klant(id, naam) VALUES(?, ?);");
		preparedStatement.setString(1, klant.getKlantId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean update(Klant klant) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("UPDATE klant SET klantId = ? WHERE klantId = ?;");
		preparedStatement.setString(1, klant.getKlantId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean delete(Klant klant) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("DELETE FROM klant WHERE klantId = '" + klant.getKlantId() + "';");
		preparedStatement.setString(1, klant.getKlantId());
		return preparedStatement.executeUpdate() == 1;
	}
	
	public Klant findById(int klantId) throws ParseException, SQLException {
		return null;
	}
}
