package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

public interface ProbleemDao {
	public List<Probleem> findAll() throws SQLException;

	public boolean save(Probleem probleem) throws SQLException;

	public boolean update(Probleem probleem) throws SQLException;

	public boolean delete(Probleem probleem) throws SQLException;

	public Probleem findByProbleemId(int probleemId);
}
