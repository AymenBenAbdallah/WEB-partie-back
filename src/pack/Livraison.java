package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Livraison {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
	Commande commande;
	
	@OneToMany(mappedBy="livraison", fetch = FetchType.EAGER)
	Collection<Produit> produits = new ArrayList<Produit>();
	
	public Integer getId() {
		return this.id;
	}
	
	public Commande getCommande() {
		return this.commande;
	}
	
	public void setCommande(Commande com) {
		this.commande = com;
	}
	
	public Collection<Produit> getProduits() {
		return this.produits;
	}
	
	public void addProduit(Produit prod) {
		this.produits.add(prod);
	}
	
}
