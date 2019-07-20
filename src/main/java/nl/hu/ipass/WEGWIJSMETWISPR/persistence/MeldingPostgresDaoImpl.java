package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Melding;

public class MeldingPostgresDaoImpl extends PostgresBaseDao implements MeldingDao {
	
	public Melding findById(int meldingId) throws ParseException, SQLException {
		return null;
	}

	@Override
	public List<Melding> findAll() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT * FROM melding;").executeQuery());
	}

	private List<Melding> getList(ResultSet resultSet) throws SQLException {
		List<Melding> meldingen = new ArrayList<>();
		while (resultSet.next()) {
			meldingen.add(toMelding(resultSet));
		}
		return meldingen;
	}

	private Melding toMelding(ResultSet resultSet) throws SQLException {
		return new Melding(resultSet.getInt("ID"), resultSet.getBoolean("BEOORDELING"),
				resultSet.getInt("PROBLEEMID"));
	}
	
	@Override
	public boolean save(Melding melding) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement(
				"INSERT INTO melding(meldingId, beoordeling, probleemId)" +
				"VALUES (?, ?, ?);");
		preparedStatement.setInt(1, melding.getMeldingId());
		preparedStatement.setBoolean(2, melding.getBeoordeling());
		preparedStatement.setInt(3, melding.getProbleemId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean update(Melding melding) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("UPDATE melding SET beoordeling = ?, probleemId = ? WHERE meldingId = ?;");
		preparedStatement.setInt(1, melding.getMeldingId());
		preparedStatement.setBoolean(2, melding.getBeoordeling());
		preparedStatement.setInt(3, melding.getProbleemId());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean delete(Melding melding) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("DELETE FROM melding WHERE meldingId = '" + melding.getMeldingId() + "';");
		preparedStatement.setInt(1, melding.getMeldingId());
		return preparedStatement.executeUpdate() == 1;
	}
}
