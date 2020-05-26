
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
@Path("/user")
public class Facade_User  {
	
	@PersistenceContext
    EntityManager em;
	
	
	@GET
    @Path("/search")
    @Produces({ "application/json" })
    public Response search() {

		Collection<User> users = em.createQuery("from User", User.class).getResultList();

		 return Response.ok(users)
				 		.status(200)
			 		 	.header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "*")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "*")
			            .header("Access-Control-Max-Age", "120")
			            .build();
	}
	
	
	
	@POST
    @Path("/add")
    @Consumes({"application/json" })
    public Response createUser(User user) {
		
		try {
			Query result = em.createQuery("from User user " + "where user.email = ?1");
			result.setParameter(1, user.email);
			
			User u = (User) result.getSingleResult();
			// On n'a pas catch d'exception donc l'utilsateur existait déjà ...
			return Response
			 		.status(490)
		 		 	.header("Access-Control-Allow-Origin", "*")
		            .header("Access-Control-Allow-Headers", "x-requested-with")
		            .header("Access-Control-Allow-Credentials", "true")
		            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		            .header("Access-Control-Max-Age", "120")
		            .build();
		}
		catch (Exception e) {
			try {
				Query result = em.createQuery("from Adresse adresse " + "where adresse.rue = ?1 and adresse.ville = ?2 and adresse.numero = ?3");
				result.setParameter(1, user.adresse.getRue());
				result.setParameter(2, user.adresse.getVille());
				result.setParameter(3, user.adresse.getNumero());
				Adresse ad = (Adresse) result.getSingleResult();
				
				
				// On n'a pas catch d'exception donc l'adresse existait déjà 
				user.setAdresse(ad);
				em.persist(user);

			}
			catch (Exception ex) {
				Adresse newAdresse = new Adresse();
				newAdresse.setNumero(user.adresse.getNumero());
				newAdresse.setVille(user.adresse.getVille());
				newAdresse.setRue(user.adresse.getRue());
				em.persist(newAdresse);
				user.setAdresse(newAdresse);
				em.persist(user);
			}
		
			Panier panier = new Panier();
			em.persist(panier);
			user.setPanier(panier);
			return Response.ok()
				 		.status(200)
			 		 	.header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "x-requested-with")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "120")
			            .build();
		}
	}
	 
	@POST
    @Path("/delete")
    @Consumes({"application/json" })
    public Response deleteUser(User user) {
		User u = em.find(User.class, user.id);
		em.remove(u);
		return Response.ok()
				 		.status(200)
			 		 	.header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "x-requested-with")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "120")
			            .build();
	}
	 
	@POST
	@Path("/modif")
	@Consumes({"application/json" })
    public Response modifUser(User user) {
		User u = em.find(User.class, user.id);
		u.setEmail(user.email);
		u.setNom(user.nom);
		u.setPrenom(user.prenom);
		u.setPassword(user.password);
		
		return Response.ok()
				 		.status(200)
			 		 	.header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "x-requested-with")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "120")
			            .build();
	}
}

