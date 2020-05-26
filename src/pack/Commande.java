package pack;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Commande {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
	int id;
	Date dcommande;
	Date dlivraison;
	
	@ManyToOne
	int userId;
	
	@OneToMany(mappedBy="commande", fetch = FetchType.EAGER)
	Collection<Livraison> livraisons = new ArrayList<Livraison>();
	
	@OneToMany(mappedBy="commande", fetch = FetchType.EAGER)
	Collection<Vendeur> vendeurs  = new ArrayList<Vendeur>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDcommande() {
		return this.dcommande;
	}
	
	public void setDcommande(Date date) {
		this.dcommande = date;
	}
	
	public Date getDlivraison() {
		return this.dcommande;
	}
	
	public void setDlivraison(Date date) {
		this.dlivraison = date;
	}
	
	public Collection<Livraison> getLivraisons() {
		return this.livraisons;
	}

	public void addLivraison(Livraison livraison) {
		this.livraisons.add(livraison);
	}

	public Collection<Vendeur> getVendeurs() {
		return this.vendeurs;
	}

	public void addVendeur(Vendeur vendeur) {
		this.vendeurs.add(vendeur);
	}
}

