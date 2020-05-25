package pack;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Produit {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
	int productId;
	
	String description;
	
	@ManyToOne
	Livraison livraison;
	
	@OneToOne
	Inventaire inventaire;
	
	float price;
	
	@OneToMany(mappedBy="produit", fetch = FetchType.EAGER)
	Collection<Avis> avis = new ArrayList<Avis>();
	
	public int getId() {
		return productId;
	}

	public void setId(int id) {
		this.productId = id;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float gePrice() {
		return this.price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Collection<Avis> getAvis() {
		return this.avis;
	}

	public void addAvis(Avis avis) {
		this.avis.add(avis);
	}

}
