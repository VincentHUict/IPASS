package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

public interface GebruikerDao {
	public String findRoleForGebruiker(String naam, String ww);
	public String getGebruikersnaam(String role);
	public boolean saveGebruiker(String gebruikersnaam, String wachtwoord);
}
