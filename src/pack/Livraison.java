package pack;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Livraison {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@OneToMany
	Commande commande;
	
	@OneToMany
	Collection<Produit>;
	
	public Integer getId() {
		return this.id;
	}
	
	public Commande getCommande() {
		return this.commande;
	}
	
	public void setCommande(Commande com) {
		this.commande = com;
	}
	
	public String getRue() {
		return this.rue;
	}
	
	public String getVille() {
		return this.ville;
	}
	
	public void setId(Integer nid) {
		this.id = nid;
}
