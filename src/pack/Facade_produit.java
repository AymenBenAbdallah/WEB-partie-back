
package pack;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jboss.logging.Logger;

import javax.ejb.Singleton;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@Singleton
@Path("/produit")
public class Facade_produit  {

	@PersistenceContext
    EntityManager em;

	@GET
    @Path("/list")
    @Produces({ "application/json" })
    public Response list() {

		Collection<Produit> produits = em.createQuery("from Produit", Produit.class).getResultList();

		return Response.ok(produits)
				 		.status(200)
			 		 	.header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "*")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "*")
			            .header("Access-Control-Max-Age", "120")
			            .build();
	}
	@POST
    @Path("/search")
    @Consumes({ "text/plain" })
	@Produces({"application/json"})
    public Response search(String motCherche) throws Exception {

		motCherche = motCherche.replace("{\"word\":\"", "");
		motCherche = motCherche.replace("\"}", "");
		
		Collection<Produit> produits = em.createQuery("from Produit", Produit.class).getResultList();
		
		try {
			Iterator<Produit> it = produits.iterator();
			Collection<Produit> result = new ArrayList<Produit>();
			while (it.hasNext()) {
				Produit prod = it.next();
				if (prod.getNom().contains(motCherche)) {
					result.add(prod);
				}
			}
			return Response.ok(result)
					 		.status(200)
				 		 	.header("Access-Control-Allow-Origin", "*")
				            .header("Access-Control-Allow-Headers", "*")
				            .header("Access-Control-Allow-Credentials", "true")
				            .header("Access-Control-Allow-Methods", "*")
				            .header("Access-Control-Max-Age", "120")
				            .build();
		} catch(Exception e) {
			throw new Exception("Là dedans");
		}
	}
	
	
	@POST
    @Path("/addDev")
    @Consumes({"application/json" })
    public Response create(Produit prod) {
		
	
		// On enregistre le produit
		em.persist(prod);
		
		
		
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
    @Path("/add")
    @Consumes({"application/json" })
    public Response create(UserEtInventaire userEtInventaire) {
		
		// On sépare les deux
		Inventaire inventaire = userEtInventaire.inventaire;
		User user = userEtInventaire.user;
		
		// On récupère le stock associé à l'user
		Stock stock = em.find(Stock.class, user.vendeur.stock);
		
		// On enregistre le produit
		em.persist(inventaire.produit);
		
		// On va chercher le bon produit
		Query query = em.createQuery("from Produit produit" + "where produit.price = ?1 and produit.nom = ?2");
		query.setParameter(1, inventaire.produit.price);
		query.setParameter(2, inventaire.produit.nom);
		Produit prod = (Produit) query.getSingleResult();
		
		// On met le bon produit dans l'inventaire
		inventaire.setProduit(prod);
		
		// On enregistre l'inventaire
		em.persist(inventaire);
		
		// On ajoute l'inventaire au stock
		stock.addInventaire(inventaire);
		
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
    public Response delete(Produit prod) {
		Produit u = em.find(Produit.class, prod.productId);
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
    public Response modif(Produit prod) {
		Produit p = em.find(Produit.class, prod.productId);
		p.setDescription(prod.description);
		p.setPrice(prod.price);
		
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
	@Path("/buy")
	@Consumes({"application/json" })
    public Response buy(UserEtProduit u) {
		User user = u.user;
		Produit prod = u.produit;
		Produit p = em.find(Produit.class, prod.productId);
		Panier panier = em.find(Panier.class, user);
		p.setPanier(panier);
		panier.setPrice(panier.price+p.price);
		panier.getProd().add(p);
		
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

