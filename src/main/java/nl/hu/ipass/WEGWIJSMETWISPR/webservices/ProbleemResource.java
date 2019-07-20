package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/problemen")
public class ProbleemResource {
	
	@GET
	@Path("alleproblemen")
	//@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllProblemen() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllProblemen());
	}

	@GET
	@Path("allemeldingen")
	//@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllMeldingen() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllMeldingen());
	}

	@GET
	@Path("faq")
	//@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllFAQs() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllFAQs());
	}

	@GET
	@Path("howto")
	//@RolesAllowed({"user", "admin"})
	@Produces("application/json")
	public String getAllHowTos() throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getAllHowTos());
	}

	@GET
	@Path("probleem_id")
	@Produces("application/json")
	public String getProbleemByProbleem_id(@PathParam("probleem_id") int probleem_id) throws SQLException, ParseException {
		Gson gson = new Gson();
		return gson.toJson(ServiceProvider.getProbleemService().getProbleemByProbleem_id(probleem_id));
	}

	@PUT
	@Path("updateprobleem")
	@Produces("application/json")
	public String updateProbleem(String msg) throws SQLException {
		Gson gson = new Gson();
		Probleem probleem = gson.fromJson(msg, Probleem.class);
		return gson.toJson(ServiceProvider.getProbleemService().updateProbleem(probleem));
	}

	@POST
	@Path("nieuwprobleem")
	//@RolesAllowed("admin")
	@Produces("application/json")
	public String newProbleem(String msg) throws SQLException {
		Gson gson = new Gson();
		Probleem probleem = gson.fromJson(msg, Probleem.class);
		return gson.toJson(ServiceProvider.getProbleemService().newProbleem(probleem));
	}

	@DELETE
	@Path("oldprobleem")
	//@RolesAllowed("admin")
	@Produces("application/json")
	public String deleteProbleem(String msg) throws SQLException {
		Gson gson = new Gson();
		Probleem probleem = gson.fromJson(msg, Probleem.class);
		return gson.toJson(ServiceProvider.getProbleemService().deleteProbleem(probleem));
	}
	
	@GET
	@Path("problemen")
	@Produces("application/json")
	public String getProblemen() throws SQLException {
		System.out.println("Probleem1");
		ProbleemService service = ServiceProvider.getProbleemService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Probleem probleem : service.getAllProblemen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("probleemId", probleem.getProbleemId());
			job.add("beschrijving", probleem.getBeschrijving());
			job.add("datum", (JsonValue) probleem.getRegistratieDatum());
			
			jab.add(job);
		}
		javax.json.JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@Path("newprobleem")
	//@RolesAllowed("user")
	@Produces("application/json")
	public Response createProbleem(@Context MySecurityContext sc,
									@FormParam("probleem_id") int probleem_id,
									@FormParam("beschrijving") String beschrijving,
									@FormParam("datum") Date datum) throws SQLException {
		System.out.println("haha");
		ProbleemService service = ServiceProvider.getProbleemService();
		boolean role = sc.isUserInRole("user");
		if (role) {
			Probleem newProbleem = service.saveProbleem(probleem_id, beschrijving, datum);
			if (newProbleem == null) {
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Probleem does not exist!");
				return Response.status(409).entity(messages).build();
			}
			return Response.ok(newProbleem).build();
		}
		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "Gebruiker is niet bevoegd!");
		return Response.status(409).entity(messages).build();
	}
}
