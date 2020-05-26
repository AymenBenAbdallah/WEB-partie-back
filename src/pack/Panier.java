package pack;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Panier {
	@Id
	int panierId;
	
	@OneToOne
	User user;
	
	float price;
	
	@OneToMany(mappedBy="panier", fetch = FetchType.EAGER)
	Collection<Produit> produits = new ArrayList<Produit>();
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User u) {
		this.user = u;
	}
	
	public void setPrice(float p) {
		this.price=p;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setProd(Collection<Produit> produits) {
		this.produits=produits;
	}
	
	public Collection<Produit> getProd(){
		return this.produits;
	}
	
}

		
	
	
