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
@Path("/panier")
public class Facade_panier {
	@PersistenceContext
    EntityManager em;

	
	@POST
    @Path("/add")
    @Consumes({"application/json" })
    public Response createCommande(Panier panier) {
		
		try {
			Query result = em.createQuery("from Panier panier " + "where panier.user = ?1");
			result.setParameter(1, panier.getUser());

			
			Panier p = (Panier) result.getSingleResult();
			// On n'a pas catch d'exception donc le panier existe déja ...
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
			
			
			em.persist(panier);
		
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
    public Response deleteAvis(Panier panier) {
		Panier p = em.find(Panier.class, panier.user);
		em.remove(p);
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
    public Response modifAvis(Panier panier) {
		Panier p = em.find(Panier.class, panier.user);
		p.setPrice(panier.price);
		
		
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
