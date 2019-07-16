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

	@POST
	@RolesAllowed({"user", "admin"})
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("gebruikersnaam") String nm, 
									 @FormParam("wachtwoord") String ww) {
		try {
			GebruikerDao dao = new GebruikerPostgresDaoImpl();
			String rol = dao.findRoleForGebruiker(nm, ww);
			
			if (rol == null) { throw new IllegalArgumentException("Geen gebruiker gevonden!"); }
			
			String token = createToken("gebruikersnaam", rol);
			
			SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
			return Response.ok(JWT).build();
		} catch (JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private String createToken(String gebruikersnaam, String rol) {
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, 30);
	
		return Jwts.builder()
				.setIssuer("http://localhost:4711/WEGWIJSMETWISPR/")
				.setSubject(gebruikersnaam)
				.setExpiration(expiration.getTime())
				.claim("rol", rol)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
}
