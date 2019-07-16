package nl.hu.ipass.WEGWIJSMETWISPR.model;

public class Overzicht {
	private String overzichtId;
	private String faq;
	private String howTo;
	private int meldingId;

	public Overzicht() {
	}

	public Overzicht(String overzichtId, String faq, String howTo, int meldingId) {
		this.overzichtId = overzichtId;
		this.faq = faq;
		this.howTo = howTo;
		this.meldingId = meldingId;
	}

	public String getOverzichtId() {
		return this.overzichtId;
	}

	public void setOverzichtId(String overzichtId) {
		this.overzichtId = overzichtId;
	}

	public String getFaq() {
		return this.faq;
	}

	public void setFaq(String faq) {
		this.faq = faq;
	}

	public String getHowTo() {
		return this.howTo;
	}

	public void setHowTo(String HowTo) {
		this.howTo = HowTo;
	}

	public int getMeldingId() {
		return this.meldingId;
	}

	public void setMeldingId(int meldingId) {
		this.meldingId = meldingId;
	}

	@Override
	public String toString()
	{
		return "Overzicht{" +
				"overzichtId='" + overzichtId + '\'' +
				", faq='" + faq + '\'' +
				", howTo='" + howTo + '\'' +
				", meldingId=" + meldingId +
				'}';
	}
}
