package pack;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int stockId;
	HashMap<Produit, Integer> inventaire = new HashMap<Produit, Integer>();
	
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getProduit(Produit prod) {
		return this.inventaire.get(prod);
	}
	public void addProduit(Produit produit, Integer quantite) {
		this.inventaire.put(produit, quantite);
	}

}
