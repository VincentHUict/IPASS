package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

public class ProbleemPostgresDaoImpl extends PostgresBaseDao implements ProbleemDao {

	@Override
	public List<Probleem> findAll() throws SQLException {
		return getList(super.getConnection().prepareStatement("SELECT * FROM probleem").executeQuery());
	}

	private List<Probleem> getList(ResultSet resultSet) throws SQLException {
		List<Probleem> problemen = new ArrayList<>();
		while (resultSet.next()) {
			problemen.add(toProbleem(resultSet));
		}
		return problemen;
	}

	private Probleem toProbleem(ResultSet resultSet) throws SQLException {
		return new Probleem(resultSet.getInt("ID"), resultSet.getString("BESCHRIJVING"), resultSet.getDate("DATUM"));
	}
	
	@Override
	public boolean save(Probleem probleem) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement(
				"INSERT INTO probleem(id, registratiedatum) VALUES(?, ?)");
		preparedStatement.setInt(1, probleem.getProbleem_id());
		preparedStatement.setDate(2, (Date) probleem.getRegistratieDatum());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean update(Probleem probleem) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement(
				"UPDATE probleem SET datum = ? WHERE probleemId = ?");
		preparedStatement.setInt(1, probleem.getProbleem_id());
		preparedStatement.setDate(2, (Date) probleem.getRegistratieDatum());
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public boolean delete(Probleem probleem) throws SQLException {
		PreparedStatement preparedStatement = super.getConnection().prepareStatement("DELETE FROM probleem WHERE probleemId = '" + probleem.getProbleem_id() + "'");
		preparedStatement.setInt(1, probleem.getProbleem_id());
		return preparedStatement.executeUpdate() == 1;
	}
	
	public List<Probleem> findById(String probleemId) throws ParseException, SQLException {
		return getList(super.getConnection().prepareStatement("SELECT id FROM probleem WHERE id = ?").executeQuery());
	}

	@Override
	public Probleem findByProbleem_id(int probleem_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
