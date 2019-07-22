package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.sql.Date;

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

import nl.hu.ipass.WEGWIJSMETWISPR.model.Limiet;
import nl.hu.ipass.WEGWIJSMETWISPR.model.Transactie;
import nl.hu.ipass.WEGWIJSMETWISPR.model.LimietDAO;
import nl.hu.ipass.WEGWIJSMETWISPR.model.LimietPostgresDaoImpl;
import nl.hu.ipass.WEGWIJSMETWISPR.model.TransactieDAO;
import nl.hu.ipass.WEGWIJSMETWISPR.model.TransactiePostgresDaoImpl;
import nl.hu.ipass.WEGWIJSMETWISPR.model.TransactieService;

@Path("/transacties")
public class ServiceProvider {
	private static ProbleemService probleemService = new ProbleemService();

	public static ProbleemService getProbleemService() {
		return probleemService;
	}
	
	private TransactieService service = new TransactieService();

	public TransactieService getTransactieService() {
		return service;
	}
	
	@POST
	@Path("post/limiet")
	public Response save(@FormParam("maxbedrag") double maxBedrag, @FormParam("categorie") String categorie, @FormParam("begindatum") Date begindatum, @FormParam("einddatum") Date einddatum) {
		LimietDAO dao = new LimietPostgresDaoImpl(); 
		
		Limiet limiet = new Limiet();
		limiet.setmaxBedrag(maxBedrag);
		limiet.setCategorie(categorie);
		limiet.setbeginDatum(begindatum);
		limiet.seteindDatum(einddatum);
	
		boolean limietOpgeslagen = dao.save(limiet); 
		
		System.out.println("limietOpgeslagen" + limietOpgeslagen);
		if(!limietOpgeslagen) {
			System.out.println("niet opgeslagen");
			return Response.status(403).build();
		}else {
			return Response.ok().build();
		}
	}

	@Path("limiet")
	@GET
	@Produces("application/json")
	public String getLimieten() {
		System.out.println("limiettest");

		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Limiet l : service.getAllLimieten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("maxbedrag", l.getmaxBedrag());
			job.add("categorie", l.getCategorie());
			job.add("begindatum", l.getbeginDatum().toString());
			job.add("einddatum", l.geteindDatum().toString());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@PUT
	@Path("/update/{id}")
	@Produces("application/json")
	public Response updateTransactie(
		
			@PathParam("id") int id, 
			@FormParam("bedrag") double bedrag,
			@FormParam("categorie") String categorie) {
		
		System.out.println("update: " + id);
		System.out.println("bedrag: " + bedrag);
		System.out.println("categorie: " + categorie);
		TransactiePostgresDaoImpl db = new TransactiePostgresDaoImpl();
		db.update(id, bedrag, categorie);

		if (!db.update(id, bedrag, categorie)) {
			System.out.println("update failed : " + id);
			return Response.status(400).build();
			} else {
				System.out.println("update ok : " + id);
				return Response.ok().build();
			}
	}
	
	@Path("delete/{id}")
	@DELETE
	@Produces("appplication/json")

	public Response deleteLimiet(@PathParam("maxbedrag") double maxBedrag) {

		LimietPostgresDaoImpl db = new LimietPostgresDaoImpl();
		Limiet limiet = new Limiet();
		limiet.setmaxBedrag(maxBedrag);
		db.delete(limiet);
		
		boolean isHetVerwijderd = db.delete(limiet);
		System.out.println("isVerwijderd " + isHetVerwijderd);
		if(!isHetVerwijderd) {
			System.out.println("kapoet");
			return Response.status(403).build();
		} else {
			return Response.ok().build();
		}
	}
}
