package pack;

import java.util.*;

import javax.persistence.*;
@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int stockId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="stock", fetch = FetchType.EAGER)
	Collection<Inventaire> inventaires = new ArrayList<Inventaire>();
	
	@OneToOne
	Vendeur vendeur;
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public Collection<Inventaire> getInventaire() {
		return this.inventaires;
	}
	public void addInventaire(Inventaire inventaire) {
		
		this.inventaires.add(inventaire);
	}
	
	public Vendeur getVendeur() {
		return this.vendeur;
	}
	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}
}
