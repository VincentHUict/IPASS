package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Medewerker;

public interface MedewerkerDao {
	public List<Medewerker> findAll() throws SQLException;

	public boolean save(Medewerker medewerker) throws SQLException;

	public boolean update(Medewerker medewerker) throws SQLException;

	public boolean delete(Medewerker medewerker) throws SQLException;
	
	public ArrayList<Medewerker> findByGeboortedatum(String gbdatum) throws SQLException;
	
	public List<Medewerker> findRoleForGebruiker(String naam, String wachtwoord);
}
