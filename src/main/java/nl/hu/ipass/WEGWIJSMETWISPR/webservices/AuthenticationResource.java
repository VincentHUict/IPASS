package nl.hu.ipass.WEGWIJSMETWISPR.webservices;

import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.ipass.WEGWIJSMETWISPR.persistence.GebruikerDao;
import nl.hu.ipass.WEGWIJSMETWISPR.persistence.GebruikerPostgresDaoImpl;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	private GebruikerDao dao = new GebruikerPostgresDaoImpl();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, 
									 @FormParam("password") String password) {
		try {
			GebruikerDao dao = new GebruikerPostgresDaoImpl();
			String role = dao.findRoleForGebruiker(username, password);
			
			if (role == null) { throw new IllegalArgumentException("Geen gebruiker gevonden!"); }
			
			String token = createToken(username, role);
			
			SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
			return Response.ok(JWT).build();
		} catch (JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private String createToken(String username, String role) {
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, 30);
	
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(expiration.getTime())
				.claim("role", role)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
	
	@POST
	@Path("/add")
	@Produces("application/json")
	public Response saveGebruiker ( @FormParam("gebruikersnaam") String gebruikersnaam,
									@FormParam("wachtwoord") String wachtwoord,
									@FormParam("rol") String rol) {
		
		if (dao.saveGebruiker(gebruikersnaam, wachtwoord, rol)) {
			return Response.ok().build();
		} else {
			return Response.status(400).build();
		}
	}
}
