package nl.hu.ipass.WEGWIJSMETWISPR.model;

import java.util.Date;

public class Probleem {
	private int probleem_id;
	private String beschrijving;
	private Date registratieDatum;
	
	public Probleem() {
	}
	
	public Probleem(int probleem_id, String beschrijving, Date registratieDatum) {
		this.probleem_id = probleem_id;
		this.beschrijving = beschrijving;
		this.registratieDatum = registratieDatum;
	}
	
	public int getProbleem_id() {
		return probleem_id;
	}
	
	public void setProbleem_id(int probleem_id) {
		this.probleem_id = probleem_id;
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
}
