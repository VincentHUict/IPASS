package nl.hu.ipass.WEGWIJSMETWISPR.model;

public class Klant {
	private String klantId;
	private String naam;
	
	public Klant() {
	}
	
	public Klant(String klantId, String naam) {
		this.klantId = klantId;
		this.naam = naam;
	}
	
	public String getKlantId() {
		return this.klantId;
	}

	public void setKlantId(String klantId) {
		this.klantId = klantId;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public String toString()
	{
		return "Klant{" +
				"klantId='" + klantId + '\'' +
				'}';
	}
}
