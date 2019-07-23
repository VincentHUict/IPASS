package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

//import nl.hu.ipass.WEGWIJSMETWISPR.model.Gebruiker;
import nl.hu.ipass.WEGWIJSMETWISPR.model.Probleem;
//import nl.hu.ipass.WEGWIJSMETWISPR.persistence.GebruikerDao;
//import nl.hu.ipass.WEGWIJSMETWISPR.persistence.GebruikerPostgresDaoImpl;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/dingen")
public class ProbleemResource {
//	private ProbleemService service = ServiceProvider.getProbleemService();
//	
//	@GET
//	@Path("probleem")
//	//@RolesAllowed({"user", "admin"})
//	@Produces("application/json")
//	public Response getProbleem(@PathParam("probleemId") int probleemId) throws SQLException {
//		System.out.println("probleempje hier");
//		Probleem probleem = service.getProbleemByProbleemId(probleemId);
//		
//		if (probleem == null) {
//			Map<String, String> messages = new HashMap<String, String>();
//			messages.put("error", "Probleem bestaat niet!");
//			return Response.status(409).entity(messages).build();
//		}
//		
//		return Response.ok(probleem).build();
//	}
//	
//	@GET
//	@Path("alleproblemen")
//	//@RolesAllowed({"user", "admin"})
//	@Produces("application/json")
//	public String getAllProblemen() throws SQLException {
//		Gson gson = new Gson();
//		return gson.toJson(ServiceProvider.getProbleemService().getAllProblemen());
//	}
//
//	@GET
//	@Path("allemeldingen")
//	//@RolesAllowed({"user", "admin"})
//	@Produces("application/json")
//	public String getAllMeldingen() throws SQLException {
//		Gson gson = new Gson();
//		return gson.toJson(ServiceProvider.getProbleemService().getAllMeldingen());
//	}
//
//	@GET
//	@Path("faq")
//	//@RolesAllowed({"user", "admin"})
//	@Produces("application/json")
//	public String getAllFAQs() throws SQLException {
//		Gson gson = new Gson();
//		return gson.toJson(ServiceProvider.getProbleemService().getAllFAQs());
//	}
//
//	@GET
//	@Path("howto")
//	//@RolesAllowed({"user", "admin"})
//	@Produces("application/json")
//	public String getAllHowTos() throws SQLException {
//		Gson gson = new Gson();
//		return gson.toJson(ServiceProvider.getProbleemService().getAllHowTos());
//	}
//
//	@GET
//	@Path("probleemid")
//	@Produces("application/json")
//	public String getProbleemByProbleemId(@PathParam("probleemId") int probleemId) throws SQLException, ParseException {
//		Gson gson = new Gson();
//		return gson.toJson(ServiceProvider.getProbleemService().getProbleemByProbleemId(probleemId));
//	}
//
//	@PUT
//	@Path("updateprobleem")
//	@Produces("application/json")
//	public Response updateProbleem(@Context SecurityContext sc, 
//							@FormParam("probleemId") int probleemId,
//							@FormParam("beschrijving") String beschrijving,
//							@FormParam("registratieDatum") Date registratieDatum) throws SQLException {
//		System.out.println(probleemId + beschrijving + registratieDatum);
//		boolean role = sc.isUserInRole("user");
//		if (role) {
//			Probleem probleem = service.updateProbleem(probleemId, beschrijving, registratieDatum);
//			
//			if (probleem == null) {
//				Map<String, String> messages = new HashMap<String, String>();
//				messages.put("error", "Probleem bestaat niet!");
//				return Response.status(409).entity(messages).build();
//			}
//			
//			return Response.ok(probleem).build();
//		}
//		Map<String, String> messages = new HashMap<String, String>();
//		messages.put("error", "U kan deze functie niet uitvoeren!");
//		return Response.status(409).entity(messages).build();
//	}
//	
//	@PUT
//	@Path("herzienprobleem")
//	@Produces("application/json")
//	public String updateProbleem(String msg) throws SQLException {
//		Gson gson = new Gson();
//		Probleem probleem = gson.fromJson(msg, Probleem.class);
//		return gson.toJson(ServiceProvider.getProbleemService().updateProbleem(probleem));
//	}
//
//	@POST
//	@Path("nieuwprobleem")
//	//@RolesAllowed("admin")
//	@Produces("application/json")
//	public String newProbleem(String msg) throws SQLException {
//		Gson gson = new Gson();
//		Probleem probleem = gson.fromJson(msg, Probleem.class);
//		return gson.toJson(ServiceProvider.getProbleemService().newProbleem(probleem));
//	}
//
//	@DELETE
//	@Path("deleteprobleem")
//	//@RolesAllowed({"user", "admin"})
//	@Produces("application/json")
//	public Response deleteProbleem(@Context SecurityContext sc,
//		@PathParam("probleemId") int probleemId) throws SQLException {
//			ProbleemService service = ServiceProvider.getProbleemService();
//			boolean role = sc.isUserInRole("gebruiker");
//			
//			if (role) {
//				if (service.deleteProbleem(probleemId)) {
//					return Response.ok().build();
//				}
//			}
//			Map<String, String> messages = new HashMap<String, String>();
//			messages.put("error", "Gebruiker is niet bevoegd!");
//			return Response.status(409).entity(messages).build();
//	}
//	
//	@GET
//	@Path("problemen")
//	@Produces("application/json")
//	public String getProblemen() throws SQLException {
//		System.out.println("Probleem1");
//		ProbleemService service = ServiceProvider.getProbleemService();
//		JsonArrayBuilder jab = Json.createArrayBuilder();
//		
//		for (Probleem probleem : service.getAllProblemen()) {
//			JsonObjectBuilder job = Json.createObjectBuilder();
//			job.add("probleemId", probleem.getProbleemId());
//			job.add("beschrijving", probleem.getBeschrijving());
//			job.add("registratieDatum", (JsonValue) probleem.getRegistratieDatum());
//			
//			jab.add(job);
//		}
//		javax.json.JsonArray array = jab.build();
//		return array.toString();
//	}
//	
//	@POST
//	@Path("newprobleem")
//	//@RolesAllowed("user")
//	@Produces("application/json")
//	public Response createProbleem(@Context MySecurityContext sc,
//									@FormParam("probleemId") int probleemId,
//									@FormParam("beschrijving") String beschrijving,
//									@FormParam("registratieDatum") Date registratieDatum) throws SQLException {
//		System.out.println("haha");
//		ProbleemService service = ServiceProvider.getProbleemService();
//		boolean role = sc.isUserInRole("user");
//		if (role) {
//			Probleem newProbleem = service.saveProbleem(probleemId, beschrijving, registratieDatum);
//			if (newProbleem == null) {
//				Map<String, String> messages = new HashMap<String, String>();
//				messages.put("error", "Probleem does not exist!");
//				return Response.status(409).entity(messages).build();
//			}
//			return Response.ok(newProbleem).build();
//		}
//		Map<String, String> messages = new HashMap<String, String>();
//		messages.put("error", "Gebruiker is niet bevoegd!");
//		return Response.status(409).entity(messages).build();
//	}
//	
//	@POST
//	@Path("savegebruiker")
//	@Produces("application/json")
//	public Response save(@FormParam("username") String username,
//						@FormParam("password") String password,
//						@FormParam("role") String role) {
//		
//		GebruikerDao dao = new GebruikerPostgresDaoImpl();
//		
//		Gebruiker gebruiker = new Gebruiker();
//		gebruiker.setGebruikersnaam(username);
//		gebruiker.setWachtwoord(password);
//		gebruiker.setRol("user");
//		
//		boolean userSaved = dao.save(gebruiker);
//		
//		System.out.println("userSaved: " + userSaved);
//		if (!userSaved) {
//			return Response.status(403).build();
//		} else {
//			return Response.ok().build();
//		}
//	}
}
