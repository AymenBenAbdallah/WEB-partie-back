
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
		em.persist(user);
//		 try {
//			 em.createQuery("SELECT * FROM TABLE adresses where (Rue.value = '" + user.adresse.getRue() +"') AND (Ville.value = '"+user.adresse.getVille()+"') AND (Num.value = '"+user.adresse.getNum()+"')")
//	             .getSingleResult();
//		 }catch(NoResultException noresult1) {
//			 em.persist(user.adresse);
//			 try {
//				 em.createQuery("SELECT email FROM TABLE users where email.value = '" + user.getEmail()+"'")
//	                 .getSingleResult();
//			 }catch(NoResultException noresult2) {
//				 em.persist(user);
//			 }
//		}
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
		u.setAdresse(user.adresse);
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

