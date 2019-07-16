package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Overzicht;

public interface OverzichtDao {
	public List<Overzicht> findAll() throws SQLException;
	
	public boolean save(Overzicht overzicht) throws SQLException;
	
	public boolean update(Overzicht overzicht) throws SQLException;
	
	public boolean delete(Overzicht overzicht) throws SQLException;
}
