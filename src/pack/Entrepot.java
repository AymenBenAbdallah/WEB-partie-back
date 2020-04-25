package pack;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Entrepot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int entrepotId;	
	int stockId;
	int adresseId;
	Date delaiPreparation;
	
	
	public int getEntrepotId() {
		return entrepotId;
	}
	public void setEntrepotId(int entrepotId) {
		this.entrepotId = entrepotId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getAdresseId() {
		return adresseId;
	}
	public void setAdresseId(int adresseId) {
		this.adresseId = adresseId;
	}
	public Date getDelaiPreparation() {
		return delaiPreparation;
	}
	public void setDelaiPreparation(Date delaiPreparation) {
		this.delaiPreparation = delaiPreparation;
	}
	
	

}
