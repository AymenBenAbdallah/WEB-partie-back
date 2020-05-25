
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
public class Facade_back  {
	
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

	@GET
    @Path("/listUsers")
    @Produces({ "application/json" })
    public Response listUsers() {

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
    @Path("/addUser")
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
	 @Path("/login")
	 @Consumes({"application/json"})
	 @Produces({"application/json"})
	 public String loginUser(User u) throws Exception {
		try {
			Query resultMail = em.createQuery("SELECT email FROM TABLE users where email.value = '" + u.getEmail()+"'");
		} catch (NoResultException noResultMail) {
			throw new Exception("Mail not found");
		}			 
		try {
			Query resultPass = em.createQuery("SELECT password FROM TABLE users where password.value = '" + u.getPassword() + "'");			 
		} catch (NoResultException noResultPass) {
			throw new Exception("Wrong password");
		}
		return generateToken();
	 }
}

