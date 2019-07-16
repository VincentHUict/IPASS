package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Klant;

public interface KlantDao {
	public List<Klant> findAll() throws SQLException;
	
	public boolean save(Klant klant) throws SQLException;
	
	public boolean update(Klant klant) throws SQLException;
	
	public boolean delete(Klant klant) throws SQLException;
}
