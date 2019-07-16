package nl.hu.ipass.WEGWIJSMETWISPR.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Medewerker {
	private String medewerkerId;
	private int nummer;
	private String naam;
	private Date gbdatum;
	private String adres;

	public Medewerker() {
	}

	public Medewerker(String medewerkerId, int nummer, String naam, String gbdatum, String adres) {
		Date datum = null;
		try {
			datum = new SimpleDateFormat("dd-mm-yyyy").parse(gbdatum);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.medewerkerId = medewerkerId;
		this.nummer = nummer;
		this.naam = naam;
		this.gbdatum = datum;
		this.adres = adres;
	}

	public String getMedewerkerId() {
		return this.medewerkerId;
	}

	public void setMedewerkerId(String medewerkerId) {
		this.medewerkerId = medewerkerId;
	}

	public int getNummer() {
		return this.nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Date getGbdatum() {
		return this.gbdatum;
	}

	public void setGbdatum(String gbdatum) {
		Date datum = null;
		try {
			datum = new SimpleDateFormat("dd-mm-yyyy").parse(gbdatum);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.gbdatum = datum;
	}

	public String getAdres() {
		return this.adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	@Override
	public String toString()
	{
		return "Medewerker{" +
				"medewerkerId='" + medewerkerId + '\'' +
				", nummer=" + nummer +
				", naam='" + naam + '\'' +
				", gbdatum=" + gbdatum +
				", adres='" + adres + '\'' +
				'}';
	}
}
