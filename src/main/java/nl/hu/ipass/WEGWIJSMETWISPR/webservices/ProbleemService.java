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
//        ProbleemPostgresDaoImpl probleemPostgresDao = new ProbleemPostgresDaoImpl();
//        return probleemPostgresDao.findAll();
//  }
	
	public Probleem getProbleemByProbleem_id(int probleem_id) throws SQLException {
		Probleem result = null;
		
		for (Probleem p : probleemDao.findAll()) {
			if (p.getProbleemId() == probleem_id) {
				result = p;
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

	public Probleem saveProbleem(int probleem_id, String beschrijving, Date registratieDatum) throws SQLException {
		Probleem probleem = new Probleem(probleem_id, beschrijving, registratieDatum);
		probleem.setProbleemId(probleem_id);
		probleem.setBeschrijving(beschrijving);
		probleem.setRegistratieDatum(registratieDatum);
		
		return (probleemDao.save(probleem)) ? probleem : null;
	}
	
	public boolean deleteProbleem(int probleem_id) throws SQLException {
		boolean verwijderd = false;
		
		Probleem p = probleemDao.findByProbleem_id(probleem_id);
		if (p != null) {
			verwijderd = probleemDao.delete(p);
		} else {
			throw new IllegalArgumentException("Code bestaat niet!");
		}
		return verwijderd;
	}

	public Probleem updateProbleem(int probleem_id, String beschrijving, Date datum) throws SQLException {
		Probleem probleem = probleemDao.findByProbleem_id(probleem_id);
			probleem.setProbleemId(probleem_id);
			probleem.setBeschrijving(beschrijving);
			probleem.setRegistratieDatum(datum);
			if (probleemDao.update(probleem)) {
				return probleemDao.findByProbleem_id(probleem_id);
			}
		return probleem;
	}
}
