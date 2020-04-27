package pack;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Produit {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
	int productId;
	
	String description;
	
	int stockId;
	float price;
	Collection<Avis> avis;
	
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
