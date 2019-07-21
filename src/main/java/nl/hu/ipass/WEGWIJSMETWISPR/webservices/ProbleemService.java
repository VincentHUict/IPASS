package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import nl.hu.ipass.WEGWIJSMETWISPR.persistence.*;
import nl.hu.ipass.WEGWIJSMETWISPR.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProbleemService {
	
	ProbleemDao probleemDao = new ProbleemPostgresDaoImpl();
	
	public List<Probleem> getAllProblemen() throws SQLException {
		return probleemDao.findAll();
	}
	
//	public List<Probleem> getAllProblemen() throws SQLException {
//      ProbleemPostgresDaoImpl probleemPostgresDao = new ProbleemPostgresDaoImpl();
//      return probleemPostgresDao.findAll();
//  }
	
	public Probleem getProbleemByProbleemId(int probleemId) throws SQLException {
		Probleem result = null;
		
		for (Probleem probleem : probleemDao.findAll()) {
			if (probleem.getProbleemId() == probleemId) {
				result = probleem;
				break;
			}
		}
		return result;
	}


    public List<Melding> getAllMeldingen() throws SQLException {
        MeldingPostgresDaoImpl meldingPostgresDao = new MeldingPostgresDaoImpl();
        return meldingPostgresDao.findAll();
    }

    public List<Overzicht> getAllFAQs() throws SQLException {
        OverzichtPostgresDaoImpl overzichtPostgresDao = new OverzichtPostgresDaoImpl();
        return overzichtPostgresDao.findAllFAQs();
    }
    
    public List<Overzicht> getAllHowTos() throws SQLException {
        OverzichtPostgresDaoImpl overzichtPostgresDao = new OverzichtPostgresDaoImpl();
        return overzichtPostgresDao.findAllHowTos();
    }

    public boolean newProbleem(Probleem probleem) throws SQLException {
        ProbleemPostgresDaoImpl probleemPostgresDao = new ProbleemPostgresDaoImpl();
        return probleemPostgresDao.save(probleem);
    }
    
    public boolean updateProbleem(Probleem probleem) throws SQLException {
        ProbleemPostgresDaoImpl probleemPostgresDao = new ProbleemPostgresDaoImpl();
        return probleemPostgresDao.update(probleem);
    }

    public boolean deleteProbleem(Probleem probleem) throws SQLException {
        ProbleemPostgresDaoImpl probleemPostgresDao = new ProbleemPostgresDaoImpl();
        return probleemPostgresDao.delete(probleem);
    }

	public Probleem saveProbleem(int probleemId, String beschrijving, Date registratieDatum) throws SQLException {
		Probleem probleem = new Probleem(probleemId, beschrijving, registratieDatum);
		probleem.setProbleemId(probleemId);
		probleem.setBeschrijving(beschrijving);
		probleem.setRegistratieDatum(registratieDatum);
		
		return (probleemDao.save(probleem)) ? probleem : null;
	}
	
	public boolean deleteProbleem(int probleemId) throws SQLException {
		boolean verwijderd = false;
		
		Probleem p = probleemDao.findByProbleemId(probleemId);
		if (p != null) {
			verwijderd = probleemDao.delete(p);
		} else {
			throw new IllegalArgumentException("Code bestaat niet!");
		}
		return verwijderd;
	}

	public Probleem updateProbleem(int probleemId, String beschrijving, Date registratieDatum) throws SQLException {
		Probleem probleem = probleemDao.findByProbleemId(probleemId);
			probleem.setProbleemId(probleemId);
			probleem.setBeschrijving(beschrijving);
			probleem.setRegistratieDatum(registratieDatum);
			if (probleemDao.update(probleem)) {
				return probleemDao.findByProbleemId(probleemId);
			}
		return probleem;
	}
}
