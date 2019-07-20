package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

public interface GebruikerDao {
	public String findRoleForGebruiker(String name, String pass);
	public String getGebruikersnaam(String role);
	public boolean saveGebruiker(String gebruikersnaam, String wachtwoord);
}
