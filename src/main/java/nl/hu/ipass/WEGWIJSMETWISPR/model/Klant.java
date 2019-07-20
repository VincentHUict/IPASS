package nl.hu.ipass.WEGWIJSMETWISPR.model;

public class Klant {
	private String klantId;
	
	public Klant() {
	}
	
	public Klant(String klantId) {
		this.klantId = klantId;
	}
	
	public String getKlantId() {
		return this.klantId;
	}

	public void setKlantId(String klantId) {
		this.klantId = klantId;
	}

	@Override
	public String toString()
	{
		return "Klant{" +
				"klantId='" + klantId + '\'' +
				'}';
	}
}
