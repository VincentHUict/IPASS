package nl.hu.ipass.WEGWIJSMETWISPR.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nl.hu.ipass.WEGWIJSMETWISPR.persistence.PostgresBaseDao;
import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

public class ProbleemPostgresDaoImpl extends PostgresBaseDao implements ProbleemDao {
	
	public ArrayList<Probleem> findAll() {
		return this.getProblemen();
	}

	public ArrayList<Probleem> getProblemen() {
		try (Connection conn = super.getConnection()) {
			ArrayList<Probleem> result = new ArrayList<Probleem>();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM probleem");
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Probleem probleem = new Probleem(rs.getInt("id"), rs.getString("beschrijving"), rs.getDate("datum"));
				
				result.add(probleem);
			}
			
			System.out.println("result: " + result);
			return result;
		} catch (SQLException exc) {
			exc.printStackTrace();
			return null;
		}
	}

//	private List<Probleem> getList(ResultSet resultSet) throws SQLException {
//		List<Probleem> problemen = new ArrayList<>();
//		while (resultSet.next()) {
//			problemen.add(toProbleem(resultSet));
//		}
//		return problemen;
//	}
//
//	private Probleem toProbleem(ResultSet resultSet) throws SQLException {
//		return new Probleem(resultSet.getInt("ID"), resultSet.getString("BESCHRIJVING"), resultSet.getDate("DATUM"));
//	}
	
	@Override
	public boolean save(Probleem probleem) {
		
		try (Connection conn = super.getConnection()) {
			
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO probleem(id, beschrijving, datum) VALUES(?, ?, ?)");
					pstmt.setInt(1, probleem.getProbleemId());
					pstmt.setString(2, probleem.getBeschrijving());
					pstmt.setDate(3, probleem.getRegistratieDatum());
					
					pstmt.executeUpdate();
					return true;
		} catch (SQLException exc) {
			exc.printStackTrace();
			return false;
		}
	}

	public boolean updateProbleem(int id, String beschrijving, Date datum) {
		try (Connection conn = super.getConnection()) {
			String query = "UPDATE probleem SET beschrijving = " + beschrijving + " WHERE id = " + id + "";
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		} catch (Exception exc) {
			System.out.println(exc);
			return false;
		}
	}
	
//	"UPDATE probleem SET datum = ? WHERE probleemId = ?;");
//	UPDATE probleem SET beschrijving = 'Bla is het probleem' WHERE id = 3;	

	public boolean deleteProbleem(Probleem probleem) {
		try {
			Connection conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM probleem WHERE id = ?");
			pstmt.setInt(1, probleem.getProbleemId());
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
	
//	
//	public List<Probleem> findById(String probleemId) throws SQLException {
//		return getList(super.getConnection().prepareStatement("SELECT id FROM probleem WHERE id = ?;").executeQuery());
//	}

	@Override
	public Probleem findByProbleemId(int probleemId) {
		// TODO Auto-generated method stub
		return null;
	}
}
