package pack;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Vendeur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@OneToOne
	User user;
	
	@OneToOne
	Stock stock;
	
	@ManyToOne
	Commande commande;
	
	@OneToMany
	Collection<Avis> avis = new ArrayList<Avis>();
	
	public Integer getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public void setStock(Stock s) {
		this.stock = s;
	}
	public Collection<Avis> getAvis() {
		return this.avis;
	}
	
	public void addAvis(Avis a) {
		this.avis.add(a);
	}
	
}
