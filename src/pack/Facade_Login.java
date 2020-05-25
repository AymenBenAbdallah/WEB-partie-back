
package pack;

import java.util.Collection;

import java.lang.Math;

import javax.ejb.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
@Path("/")
public class Facade_Login  {
	
	static int taille_token = 22;

	
	@PersistenceContext
    EntityManager em;
		
	static String generateToken() {
		String token = "";
		for (int i = 0; i < taille_token; i++) {
			token += (char) ((char) (Math.random() * ( 126 - 32 )) + 32);
		}
		
		return token;
	}

	

	
	 @POST
	 @Path("/login")
	 @Consumes({"application/json"})
	 @Produces({"application/json"})
	 public Response loginUser(User u) throws Exception {
		try {
			Query result = em.createQuery("from User user " + "where user.email = ?1 and user.password = ?2");
			result.setParameter(1, u.email);
			result.setParameter(2, u.password);
			
			User user = (User) result.getSingleResult();
			String token = generateToken();
			return Response.ok(new Object[]{user, token})
			 		.status(200)
		 		 	.header("Access-Control-Allow-Origin", "*")
		            .header("Access-Control-Allow-Headers", "*")
		            .header("Access-Control-Allow-Credentials", "true")
		            .header("Access-Control-Allow-Methods", "*")
		            .header("Access-Control-Max-Age", "120")
		            .build();
		} catch (NoResultException noResultMail) {
			throw new Exception("Invalid credentials");
		}			 
		
		
	 }
}

