package nl.hu.ipass.WEGWIJSMETWISPR.model;

public class Overzicht {
	private static String overzichtId;
	private String FAQ;
	private String HowTo;
	private int meldingId;
	
	public Overzicht() {
	}
	
	public Overzicht(String overzichtId, String FAQ, String HowTo, int meldingId) {
		this.overzichtId = overzichtId;
		this.FAQ = FAQ;
		this.HowTo = HowTo;
		this.meldingId = meldingId;
	}
	
	public static String getOverzichtId() {
		return overzichtId;
	}
	
	public void setOverzichtId(String overzichtId) {
		Overzicht.overzichtId = overzichtId;
	}
	
	public String getFAQ() {
		return this.FAQ;
	}
	
	public void setFAQ(String FAQ) {
		this.FAQ = FAQ;
	}
	
	public String getHowTo() {
		return this.HowTo;
	}
	
	public void setHowTo(String HowTo) {
		this.HowTo = HowTo;
	}
	
	public int getMeldingId() {
		return this.meldingId;
	}
	
	public void setMeldingId(int meldingId) {
		this.meldingId = meldingId;
	}
}
