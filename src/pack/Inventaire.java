package pack;



import javax.persistence.*;

@Entity
public class Inventaire {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int inventaireId;
	
	
	@ManyToOne
	Stock stock;
	
	@OneToOne
	Produit produit;
	
	Integer quantite;
	
	public int getId() {
		return inventaireId;
	}

	public void setId(int id) {
		this.inventaireId = id;
	}

	public Produit getProduit() {
		return this.produit;
	}
	
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public int getQuantite() {
		return this.quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
}
