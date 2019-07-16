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
			if (p.getProbleem_id() == probleem_id) {
				result = p;
				break;
			}
		}
		return result;
	}
	
//	public List<Probleem> getProbleemById(String probleemId) throws SQLException, ParseException {
//      ProbleemPostgresDaoImpl probleemPostgresDao = new ProbleemPostgresDaoImpl();
//	    return probleemPostgresDao.findById(probleemId);
//	}

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
		Probleem p = new Probleem(probleem_id, beschrijving, registratieDatum);
		p.setProbleem_id(probleem_id);
		p.setBeschrijving(beschrijving);
		p.setRegistratieDatum(registratieDatum);
		
		if (probleemDao.save(p)) return p;
		return null;
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
		Probleem p = probleemDao.findByProbleem_id(probleem_id);
			p.setProbleem_id(probleem_id);
			p.setBeschrijving(beschrijving);
			p.setRegistratieDatum(datum);
			if (probleemDao.update(p)) {
				return probleemDao.findByProbleem_id(probleem_id);
			}
		return p;
	}
}
