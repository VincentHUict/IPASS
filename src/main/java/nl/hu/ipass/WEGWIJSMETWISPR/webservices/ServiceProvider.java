package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;
import nl.hu.ipass.WEGWIJSMETWISPR.persistence.ProbleemDao;
import nl.hu.ipass.WEGWIJSMETWISPR.persistence.ProbleemPostgresDaoImpl;

/*
 * Dit is de URI die je aanroept om de JSON terug te krijgen
 */

@Path("/problemen")
public class ServiceProvider {
	private ProbleemService probleemService = new ProbleemService();

	public ProbleemService getProbleemService() {
		return probleemService;
	}
	
	/*
	 * Deze methode is om een probleem aan te maken
	 */
	
	@POST
	@Path("post/probleem")
	public Response save(@FormParam("probleemid") int probleemId, @FormParam("beschrijving") String beschrijving, @FormParam("registratiedatum") Date registratieDatum) {
		ProbleemDao dao = new ProbleemPostgresDaoImpl();
		
		Probleem probleem = new Probleem();
		probleem.setProbleemId(probleemId);
		probleem.setBeschrijving(beschrijving);
		probleem.setRegistratieDatum(registratieDatum);
		
		boolean probleemSaved = dao.save(probleem);
		
		System.out.println("probleemSaved" + probleemSaved);
		if (!probleemSaved) {
			System.out.println("Probleem niet opgeslagen!");
			return Response.status(403).build();
		} else {
			return Response.ok().build();
		}
	}
	
	/*
	 * Met deze methode kan je alle problemen ophalen uit de DB
	 */
	
	@Path("get/probleem")
	@GET
	@Produces("application/json")
	public String getProblemen() {
		System.out.println("testtest");
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Probleem probleem : probleemService.getAllProblemen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("probleemid", probleem.getProbleemId());
			job.add("beschrijving", probleem.getBeschrijving());
			job.add("registratiedatum", probleem.getRegistratieDatum().toString());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	/*
	 * Deze methode kan je probleem updaten, bijvoorbeeld als je een andere beschrijving wilt 
	 */
	
	@PUT
	@Path("update/probleem/{id}")
	@Produces("application/json")
	public Response updateProbleem (
				@PathParam("id") int id,
				@PathParam("beschrijving") String beschrijving,
				@FormParam("datum") Date datum) {
		
		System.out.println("Update id: " + id);
		System.out.println("Nieuwe beschrijving: " + beschrijving);
		ProbleemPostgresDaoImpl db = new ProbleemPostgresDaoImpl();
		db.updateProbleem(id, beschrijving, datum);
		
		if (!db.updateProbleem(id, beschrijving, datum)) {
			System.out.println("Update niet doorgevoerd: " + id);
			return Response.status(400).build();
		} else {
			System.out.println("Update doorgevoerd: " + id);
			return Response.ok().build();
		}
	}
	
	/*
	 * Met deze methode kan je een probleem verwijderen uit de DB
	 */
	
	@Path("delete/probleem/{id}")
	@DELETE
	@Produces("application/json")
	public Response deleteProbleem(@PathParam("id") int probleemid) {

		ProbleemPostgresDaoImpl db = new ProbleemPostgresDaoImpl();
		Probleem probleem = new Probleem();
		probleem.setProbleemId(probleemid);
		db.deleteProbleem(probleem);
		
		boolean Verwijderd = db.deleteProbleem(probleem);
		System.out.println("Probleem " + probleemid + " verwijderd: " + Verwijderd);
		if (!Verwijderd) {
			System.out.println("Broken");
			return Response.status(403).build();
		} else {
			return Response.ok().build();
		}
	}
}
