package pack;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
	int id;

	@Column(name = "SCN", nullable = false, unique = true)
    private String scn;
	
	String nom;
	String prenom;
	String email;
	String password;
	
	@OneToOne
	Adresse adresse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String name) {
		this.nom = name;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public void setPrenom(String name) {
		this.prenom = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	public void getPassword() {
		return this.password;
	}
	
	public String setPassword(String password) {
		this.password = password;		
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}

