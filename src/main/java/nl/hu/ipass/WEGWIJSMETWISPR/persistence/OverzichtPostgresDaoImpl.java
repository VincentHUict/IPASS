package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Overzicht;

public class OverzichtPostgresDaoImpl extends PostgresBaseDao implements OverzichtDao {

	@Override
	public List<Overzicht> findAll() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT * FROM medewerker").executeQuery());
	}
	
	private List<Overzicht> getList(ResultSet resultSet) throws SQLException {
		List<Overzicht> overzichten = new ArrayList<>();
		while (resultSet.next()) {
			overzichten.add(toOverzicht(resultSet));
		}
		return overzichten;
	}

	private Overzicht toOverzicht(ResultSet resultSet) throws SQLException {
		return new Overzicht(resultSet.getString("ID"), resultSet.getString("FAQ"),
				resultSet.getString("HOWTO"), resultSet.getInt("MELDINGID"));
	}
	
	@Override
	public boolean save(Overzicht overzicht) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement(
				"INSERT INTO overzicht(id, faq, how_to, meldingId) VALUES(?, ?, ?, ?)");
		preparedStatement.setString(1, Overzicht.getOverzichtId());
		preparedStatement.setString(2, overzicht.getFAQ());
		preparedStatement.setString(3, overzicht.getHowTo());
		preparedStatement.setInt(4, overzicht.getMeldingId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean update(Overzicht overzicht) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("UPDATE overzicht SET faq = ?, how_to = ?, meldingId = ?  WHERE overzichtId = ?");
		preparedStatement.setString(1, Overzicht.getOverzichtId());
		preparedStatement.setString(2, overzicht.getFAQ());
		preparedStatement.setString(3, overzicht.getHowTo());
		preparedStatement.setInt(4, overzicht.getMeldingId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean delete(Overzicht overzicht) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("DELETE FROM overzicht WHERE overzichtId = '" + Overzicht.getOverzichtId() + "'");
		preparedStatement.setString(1, Overzicht.getOverzichtId());
		return preparedStatement.executeUpdate() == 1;
	}

	public List<Overzicht> findAllFAQs() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT faq FROM overzicht").executeQuery());
	}
	
	public List<Overzicht> findAllHowTos() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT how_to FROM overzicht").executeQuery());
	}
}
