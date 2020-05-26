package pack;

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
	Collection<Produit> prod = new ArrayList<Produit>();
	
	
	public void setPanierId(int p) {
		this.panierId=p;
	}
	
	public int getPanierId() {
		return this.panierId;
	}
	
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
	
	public void setProd(Produit prod) {
		this.prod.add(prod);
	}
	
	public Collection<Produit> getProd(){
		return this.prod;
	}
	
}

		
	
	
