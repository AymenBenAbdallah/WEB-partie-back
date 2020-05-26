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
@Path("/avis_prod")
public class Facade_Avis_prod {
	@PersistenceContext
    EntityManager em;

	
	@POST
    @Path("/add")
    @Consumes({"application/json" })
    public Response createAvis(Avis avis) {
		
		try {
			Query result = em.createQuery("from Avis avis " + "where avis.produit = ?1 and avis.user = ?2");
			result.setParameter(1, avis.produit);
			result.setParameter(2, avis.user);

			
			Avis a = (Avis) result.getSingleResult();
			// On n'a pas catch d'exception donc l'avis existait déjà ...
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
			
			em.persist(avis);
		
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
    public Response deleteAvis(Avis avis) {
		Avis a = em.find(Avis.class, avis.avis);
		em.remove(a);
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
    public Response modifAvis(Avis avis) {
		Avis a = em.find(Avis.class, avis.avis);
		a.setNote(avis.note);
		a.setCommentaire(avis.commentaire);
		
		
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
