package nl.hu.ipass.WEGWIJSMETWISPR.model;

import java.sql.Date;

public class Probleem {
	private int probleemId;
	private String beschrijving;
	private Date registratieDatum;
	
	public Probleem() {
	}
	
	public Probleem(int probleemId, String beschrijving, Date registratieDatum) {
		this.probleemId = probleemId;
		this.beschrijving = beschrijving;
		this.registratieDatum = registratieDatum;
	}
	
	public int getProbleemId() {
		return probleemId;
	}
	
	public void setProbleemId(int probleemId) {
		this.probleemId = probleemId;
	}
	
	public Date getRegistratieDatum() {
		return registratieDatum;
	}
	
	public void setRegistratieDatum(Date registratieDatum) {
		this.registratieDatum = registratieDatum;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	@Override
	public String toString()
	{
		return "Probleem{" +
				"probleemId=" + probleemId +
				", beschrijving='" + beschrijving + '\'' +
				", registratieDatum=" + registratieDatum +
				'}';
	}
}
