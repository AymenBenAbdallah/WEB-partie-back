package pack;

import javax.persistence.*;

@Entity 
public class Adresse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String rue;
	String ville;
	int numero;
	@OneToOne
	User resident;
	
	public Integer getId() {
		return this.id;
	}
	
	public int getNum() {
		return this.numero;
	}
	
	public void setNum(int num) {
		this.numero = num;
	}
	
	public String getRue() {
		return this.rue;
	}
	
	public String getVille() {
		return this.ville;
	}
	
	public void setId(Integer nid) {
		this.id = nid;
	}
	
	public void setRue(String nrue) {
		this.rue = nrue;
	}
	
	public void setVille(String nville) {
		this.ville = nville;
	}
	
	public void setResident(User p) {
		this.resident = p;
	}
	
	public User getResident() {
		return this.resident;
	}
 }

