package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

public class ServiceProvider {
	private static ProbleemService probleemService = new ProbleemService();

	public static ProbleemService getProbleemService() {
		return probleemService;
	}
}
