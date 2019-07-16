package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Medewerker;

public class MedewerkerPostgresDaoImpl extends PostgresBaseDao implements MedewerkerDao {

	@Override
	public List<Medewerker> findAll() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT * FROM medewerker").executeQuery());
	}

	private List<Medewerker> getList(ResultSet resultSet) throws SQLException {
		List<Medewerker> medewerkers = new ArrayList<>();
		while (resultSet.next()) {
			medewerkers.add(toMedewerker(resultSet));
		}
		return medewerkers;
	}

	private Medewerker toMedewerker(ResultSet resultSet) throws SQLException {
		return new Medewerker(resultSet.getString("ID"), resultSet.getInt("NUMMER"),
				resultSet.getString("NAAM"), resultSet.getString("DATUM"),
				resultSet.getString("ADRES"));
	}

	@Override
	public boolean save(Medewerker medewerker) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement(
				"INSERT INTO medewerker(id, nummer, naam, geboortedatum, adres)" +
				"VALUES(?, ?, ?, ?, ?)");
		preparedStatement.setString(1, medewerker.getMedewerkerId());
		preparedStatement.setInt(2, medewerker.getNummer());
		preparedStatement.setString(3, medewerker.getNaam());
		preparedStatement.setDate(4, (Date) medewerker.getGbdatum());
		preparedStatement.setString(5, medewerker.getAdres());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean update(Medewerker medewerker) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("UPDATE medewerker SET nummer = ?, naam = ?, gbdatum = ?, adres = ? WHERE medewerkerId = ?");
		preparedStatement.setString(1, medewerker.getMedewerkerId());
		preparedStatement.setInt(2, medewerker.getNummer());
		preparedStatement.setString(3, medewerker.getNaam());
		preparedStatement.setDate(4, (Date) medewerker.getGbdatum());
		preparedStatement.setString(5, medewerker.getAdres());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean delete(Medewerker medewerker) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("DELETE FROM medewerker WHERE medewerkerId = '" + medewerker.getMedewerkerId() + "'");
		preparedStatement.setString(1, medewerker.getMedewerkerId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public List<Medewerker> findRoleForGebruiker(String naam, String wachtwoord) {
		return null;
	}

	public ArrayList<Medewerker> findByGeboortedatum(String gbdatum) throws SQLException {
		ArrayList<Medewerker> mGeboortedatum = new ArrayList<>();
		for (Medewerker medewerker : this.findAll()) {
			if (medewerker.getGbdatum() != null) {
				if (medewerker.getGbdatum().toString().equals(gbdatum)) {
					mGeboortedatum.add(medewerker);
				}
			}
		}
		return mGeboortedatum;
	}
}
