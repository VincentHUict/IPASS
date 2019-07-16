package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.SQLException;
import java.text.ParseException;

import com.google.gson.Gson;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;

@Path("/problemen")
public class ProbleemResource {
	@GET
	@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllProblemen() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllProblemen());
	}
	
	@GET
	@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllMeldingen() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllMeldingen());
	}
	
	@GET
	@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllFAQs() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllFAQs());
	}
	
	@GET
	@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllHowTos() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllHowTos());
	}
	
	@GET
	@Path("{probleem_id}")
	@Produces("application/json")
	public String getProbleemByProbleem_id(@PathParam("probleem_id") int probleem_id) throws SQLException, ParseException {
		Gson gson = new Gson();
		if (probleem_id.equals(Probleem.getProbleem_id())) {
			return gson.toJson(ServiceProvider.getProbleemService().getProbleemByProbleem_id(probleem_id));
		}
		return probleem_id;
	}
	
	@PUT
	@Produces("application/json")
	public String updateProbleem(String msg) throws SQLException {
		Gson gson = new Gson();
		Probleem probleem = gson.fromJson(msg, Probleem.class);
		return gson.toJson(ServiceProvider.getProbleemService().updateProbleem(probleem));
	}
	
	@POST
	@RolesAllowed("admin")
	@Produces("application/json")
	public String newProbleem(String msg) throws SQLException {
		Gson gson = new Gson();
		Probleem probleem = gson.fromJson(msg, Probleem.class);
		return gson.toJson(ServiceProvider.getProbleemService().newProbleem(probleem));
	}
	
	@DELETE
	@RolesAllowed("admin")
	@Produces("application/json")
	public String deleteProbleem(String msg) throws SQLException {
		Gson gson = new Gson();
		Probleem probleem = gson.fromJson(msg, Probleem.class);
		return gson.toJson(ServiceProvider.getProbleemService().deleteProbleem(probleem));
	}
}
