package nl.hu.ipass.WEGWIJSMETWISPR.model;

public class Melding {
	private int meldingId;
	private boolean beoordeling;
	private int probleemId;
	
	public Melding() {
	}
	
	public Melding(int meldingId, boolean beoordeling, int probleemId) {
		this.meldingId = meldingId;
		this.beoordeling = beoordeling;
		this.probleemId = probleemId;
	}
	
	public int getMeldingId() {
		return meldingId;
	}
	
	public void setMeldingId(int meldingId) {
		this.meldingId = meldingId;
	}
	
	public boolean getBeoordeling() {
		return this.beoordeling;
	}
	
	public void setBeoordeling(boolean beoordeling) {
		this.beoordeling = beoordeling;
	}
	
	public int getProbleemId() {
		return this.probleemId;
	}
	
	public void setProbleemId(int probleemId) {
		this.probleemId = probleemId;
	}
	
//	public boolean setBeoordeling() {	
//		try (Connection myConn = new DriverManager.getConnection()) {
//			Statement myStmt = myConn.createStatement();
//			ResultSet myRs = myStmt.executeQuery("T");
//			return true;
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//		return false;
//	}
}
