package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Date;
import java.util.ArrayList;
import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

public interface ProbleemDao {
	public ArrayList<Probleem> findAll();

	public boolean save(Probleem probleem);

	public boolean updateProbleem(int id, String beschrijving, Date datum);

	public boolean deleteProbleem(Probleem probleem);

	public Probleem findByProbleemId(int probleemId);
}
