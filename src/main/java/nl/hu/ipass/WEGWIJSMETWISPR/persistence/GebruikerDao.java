package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

public interface GebruikerDao {
	public String findRoleForGebruiker(String naam, String ww);
}
