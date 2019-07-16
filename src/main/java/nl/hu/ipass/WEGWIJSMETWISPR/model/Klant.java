package nl.hu.ipass.WEGWIJSMETWISPR.model;

public class Klant {
	private static String klantId;
	
	public Klant() {
	}
	
	public Klant(String klantId) {
		this.klantId = klantId;
	}
	
	public static String getKlantId() {
		return klantId;
	}
	
	public void setKlantId(String klantId) {
		this.klantId = klantId;
	}
}
