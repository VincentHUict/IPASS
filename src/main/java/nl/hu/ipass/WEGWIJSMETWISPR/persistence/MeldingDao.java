package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Melding;

public interface MeldingDao {
	public List<Melding> findAll() throws SQLException;
	
	public boolean save(Melding melding) throws SQLException;
	
	public boolean update(Melding melding) throws SQLException;
	
	public boolean delete(Melding melding) throws SQLException;
}
