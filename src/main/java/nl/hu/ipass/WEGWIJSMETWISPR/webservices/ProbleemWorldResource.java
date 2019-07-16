package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.json.*;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;

@Path("/problemen")
public class ProbleemWorldResource {
	
	@GET
	@Produces("application/json")
	public String getProblemen() throws SQLException {
		System.out.println("Probleem1");
		ProbleemService service = ServiceProvider.getProbleemService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Probleem probleem : service.getAllProblemen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("probleem_id", probleem.getProbleem_id());
			job.add("beschrijving", probleem.getBeschrijving());
			job.add("datum", (JsonValue) probleem.getRegistratieDatum());
			
			jab.add(job);
		}
		javax.json.JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("user")
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
