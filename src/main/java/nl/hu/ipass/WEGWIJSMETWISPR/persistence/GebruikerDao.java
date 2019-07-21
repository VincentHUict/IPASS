package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Gebruiker;

public interface GebruikerDao {
	public String findRoleForGebruiker(String name, String pass);
	public String getGebruikersnaam(String role);
	public boolean save(Gebruiker gebruiker);
	boolean saveGebruiker(String gebruikersnaam, String wachtwoord, String rol);
}
